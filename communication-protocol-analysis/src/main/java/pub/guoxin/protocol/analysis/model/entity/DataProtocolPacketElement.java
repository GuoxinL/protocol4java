package pub.guoxin.protocol.analysis.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pub.guoxin.protocol.analysis.model.TypeClass;
import pub.guoxin.protocol.analysis.model.constants.DataProtocolConstants;
import pub.guoxin.protocol.analysis.utils.ByteUtil;
import pub.guoxin.protocol.analysis.utils.BytesUtils;
import pub.guoxin.protocol.analysis.utils.Integers;

import java.io.Serializable;

/**
 * Create by guoxin on 2018/7/8
 */
@AllArgsConstructor
@Getter
public class DataProtocolPacketElement implements Serializable, ProtocolSerialization {

    private short    elementLength;
    private Object   data;
    /**
     * 该变量不参与Element序列化
     */
    private TypeClass typeClass;

    public DataProtocolPacketElement(byte[] data, DataProtocolType type, DataProtocolCode code, Integer p) {
        this.typeClass = TypeClass.findByIndex(type.getIndex());
        // 解析长度
        byte[] elementLengthBytes = BytesUtils.createByteArray(data,
                Integers.sumEqualTo(p, DataProtocolConstants.Element.ELEMENT_LENGTH_START),
                Integers.sumEqualTo(p, DataProtocolConstants.Element.ELEMENT_LENGTH_END));
        short elementLength = ByteUtil.getShort(elementLengthBytes);
        // 解析数据
        byte[] bytes = BytesUtils.createByteArray(data,
                Integers.sumEqualTo(p, DataProtocolConstants.Packet.DATA_LENGTH_END),
                Integers.sumEqualTo(p, (int) elementLength));
        Object object = ByteUtil.getObject(this.typeClass, bytes);
        this.data = object;
    }

    @Override
    public byte[] serialization() {
        return ByteUtil.getBytes(this.typeClass, this.data);
    }
}
