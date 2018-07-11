package pub.guoxin.protocol.analysis.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pub.guoxin.protocol.analysis.utils.ArrayUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * 协议：数据段 - 元素集合
 * Create by guoxin on 2018/7/8
 */
@NoArgsConstructor
@Getter
public class DataProtocolPacketElementList extends ArrayList<DataProtocolPacketElement> implements Serializable, ProtocolSerialization {

    public DataProtocolPacketElementList(int initialCapacity) {
        super(initialCapacity);
    }

    public DataProtocolPacketElementList(ByteBuffer byteBuffer, DataProtocolIndexType type, DataProtocolIndexCode code, Short elementSize) {
        this(elementSize);
        for (int i = 0; i < elementSize; i++) {
            add(new DataProtocolPacketElement(byteBuffer, type, code));
        }
    }

    public DataProtocolPacketElementList(Field declaredField, ProtocolEntity protocolEntity){
        add(new DataProtocolPacketElement(declaredField, protocolEntity));
    }

    @Override
    public byte[] serialization() {
        byte[] result = null;
        for (DataProtocolPacketElement dataProtocolPacketElement : this) {
            byte[] serialization = dataProtocolPacketElement.serialization();
            result = ArrayUtils.merge(result, serialization);
        }
        return result;
    }
}
