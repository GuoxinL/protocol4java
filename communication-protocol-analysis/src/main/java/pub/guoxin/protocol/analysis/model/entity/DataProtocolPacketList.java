package pub.guoxin.protocol.analysis.model.entity;

import pub.guoxin.protocol.analysis.model.constants.DataProtocolConstants;
import pub.guoxin.protocol.analysis.utils.ArrayUtils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Create by guoxin on 2018/7/8
 */
public class DataProtocolPacketList extends ArrayList<DataProtocolPacket> implements Serializable, ProtocolSerialization{

    public DataProtocolPacketList(int initialCapacity) {
        super(initialCapacity);
    }

    public DataProtocolPacketList(byte[] bytes, short totalPacket){
        this(totalPacket);
        Integer p = DataProtocolConstants.Packet.CODE_START;
        for (short i = 0; i < totalPacket; i++) {
            add(new DataProtocolPacket(bytes, p));
        }
    }

    @Override
    public byte[] serialization() {
        byte[] result = null;
        for (DataProtocolPacket dataProtocolPacket : this) {
            byte[] bytes = dataProtocolPacket.serialization();
            result = ArrayUtils.merge(result, bytes);
        }
        return result;
    }
}
