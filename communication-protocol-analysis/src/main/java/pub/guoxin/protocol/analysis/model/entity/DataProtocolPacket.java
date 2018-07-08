package pub.guoxin.protocol.analysis.model.entity;

import lombok.*;
import org.apache.commons.codec.DecoderException;
import pub.guoxin.protocol.analysis.model.TypeClass;
import pub.guoxin.protocol.analysis.model.anno.CodeIndex;
import pub.guoxin.protocol.analysis.model.constants.DataProtocolConstants;
import pub.guoxin.protocol.analysis.utils.*;

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
public class DataProtocolPacket implements Serializable, ProtocolSerialization {
    private DataProtocolCode              code;
    private DataProtocolType              type;
    private Short                         elementSize;
    private DataProtocolPacketElementList elements;

    public DataProtocolPacket(byte[] data, Integer p) {
        {
            byte[] bytes     = BytesUtils.createByteArray(data, Integers.sumEqualTo(p, DataProtocolConstants.Packet.CODE_START), sum(p, DataProtocolConstants.Packet.CODE_END));
            short  codeIndex = ByteUtil.getShort(bytes);
            this.code = DataProtocolCode.create(codeIndex, null);
        }
        {
            byte[] bytes     = BytesUtils.createByteArray(data, Integers.sumEqualTo(p, DataProtocolConstants.Packet.TYPE_START), sum(p, DataProtocolConstants.Packet.TYPE_END));
            short  codeIndex = ByteUtil.getShort(bytes);
            /**
             * TODO 这里在写TypeConvert时需要重写
             */
            this.type = DataProtocolType.create(codeIndex, TypeClass.findByIndex(codeIndex).getClazz());
        }
        {
            elements = new DataProtocolPacketElementList(data,this.type, this.code,elementSize, p);
        }
    }

    @Override
    public byte[] serialization() {
        byte[] result;
        {
            result = ByteUtil.getBytes(this.code.getIndex());
        }
        {
            byte[] bytes = ByteUtil.getBytes(this.type.getIndex());
            result = ArrayUtils.merge(result, bytes);
        }
        {
            byte[] bytes = ByteUtil.getBytes(this.elementSize);
            result = ArrayUtils.merge(result, bytes);
        }
        {
            byte[] bytes = elements.serialization();
            result = ArrayUtils.merge(result, bytes);
        }
        return result;
    }
}
