package pub.guoxin.protocol.analysis.model.entity;

import lombok.*;
import org.apache.commons.codec.DecoderException;
import pub.guoxin.protocol.analysis.model.TypeClass;
import pub.guoxin.protocol.analysis.model.anno.CodeIndex;
import pub.guoxin.protocol.analysis.model.constants.DataProtocolConstants;
import pub.guoxin.protocol.analysis.model.exception.ProtocolNotFoundException;
import pub.guoxin.protocol.analysis.utils.BytesUtils;
import pub.guoxin.protocol.analysis.utils.HexConvertUtils;

import java.io.Serializable;
import java.lang.reflect.Field;

import static java.lang.Integer.sum;

/**
 * 数据段
 * <p>
 * Created by guoxin on 18-2-25.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataProtocolPacket implements Serializable {
    private DataProtocolCode code;
    private DataProtocolType type;
    private Object           data;

    private void analysisPacket(String data, int pointer) throws DecoderException {
        {
            String hexString = BytesUtils.subStringByIndex(data, sum(pointer, DataProtocolConstants.Packet.CODE_START), sum(pointer, DataProtocolConstants.Packet.CODE_END));
            int    code      = HexConvertUtils.hexString2Int(hexString);
            // 没事闲的在验证一遍
            if (this.code.getIndex() == code) {
                throw new ProtocolNotFoundException("二次验证 version 失败");
            }
        }
        {
            String hexString = BytesUtils.subStringByIndex(data, sum(pointer, DataProtocolConstants.Packet.TYPE_START), sum(pointer, DataProtocolConstants.Packet.TYPE_END));
            int    type      = HexConvertUtils.hexString2Int(hexString);
            // 没事闲的在验证一遍
            if (this.type.getIndex() == type) {
                throw new ProtocolNotFoundException("二次验证 version 失败");
            }
        }
        {
            String hexStringLength = BytesUtils.subStringByIndex(data, sum(pointer, DataProtocolConstants.Packet.DATA_LENGTH_START), sum(pointer, DataProtocolConstants.Packet.DATA_LENGTH_END));
            int    dataLength      = HexConvertUtils.hexString2Int(hexStringLength);
            String hexString       = BytesUtils.subStringByIndex(data, sum(pointer, DataProtocolConstants.Packet.DATA_LENGTH_END), sum(pointer, dataLength));
            setInstanceField(this, hexString);
            pointer += DataProtocolConstants.Packet.TYPE_END + dataLength;
        }

    }

    /**
     * TODO 这里外循环是16进制字符串，内循环是对象字段，由于该方法涉及反射所以该方法循环次数将会过多，所以要减少该方法的执行次数
     *
     * @param dataProtocolPacket 数据段
     * @param hexString          16进制字符串
     * @throws DecoderException 类型解析错误
     */
    private void setInstanceField(DataProtocolPacket dataProtocolPacket, String hexString) throws DecoderException {
        DataProtocolType dataProtocolType = dataProtocolPacket.getType();
        Class<?>         clazz            = dataProtocolType.getValue();

        short    dataCodeIndex = dataProtocolPacket.getCode().getIndex();

        for (Field declaredField : clazz.getDeclaredFields()) {
            CodeIndex codeIndexAnnotation = declaredField.getAnnotation(CodeIndex.class);

            short codeIndex = codeIndexAnnotation.index();

            if (codeIndex == dataCodeIndex) {
                TypeClass typeClass  = TypeClass.findByClass(declaredField.getType());
                Object    fieldValue = HexConvertUtils.getFieldValueByDataType(typeClass, hexString);
                dataProtocolPacket.setData(fieldValue);
            }
        }
    }


}
// 字段名，字段类型