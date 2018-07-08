package pub.guoxin.protocol.analysis.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pub.guoxin.protocol.analysis.utils.ArrayUtils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Create by guoxin on 2018/7/8
 */
@NoArgsConstructor
@Getter
public class DataProtocolPacketElementList extends ArrayList<DataProtocolPacketElement> implements Serializable, ProtocolSerialization {

    public DataProtocolPacketElementList(int initialCapacity) {
        super(initialCapacity);
    }

    public DataProtocolPacketElementList(byte[] data, DataProtocolType type, DataProtocolCode code, Short elementSize, Integer p) {
        this(elementSize);
        for (int i = 0; i < elementSize; i++) {
            add(new DataProtocolPacketElement(data, type, code, p));
        }
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
