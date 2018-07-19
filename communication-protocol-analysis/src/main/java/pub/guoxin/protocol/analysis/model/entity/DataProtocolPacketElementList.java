package pub.guoxin.protocol.analysis.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pub.guoxin.protocol.analysis.conf.convert.TypeConvert;
import pub.guoxin.protocol.analysis.model.exception.ProtocolConfigException;
import pub.guoxin.protocol.analysis.utils.ArrayUtils;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.stream.IntStream;

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

    }

    public DataProtocolPacketElementList(Field declaredField, ProtocolEntity protocolEntity, Class<? extends TypeConvert> typeConvert, boolean isArray) {
        Object[] objects;
        if (isArray) {
            Object obj;
            declaredField.setAccessible(true);
            try {
                obj = declaredField.get(protocolEntity);
            } catch (IllegalAccessException e) {
                throw new ProtocolConfigException("数组", e);
            }
            //iterable = Arrays.asList((Object[])object);
            //以上方式无法对基本数据类型数组进行转换，
            objects = IntStream.range(0, Array.getLength(obj)).mapToObj(i -> Array.get(obj, i)).toArray();
        } else {
            objects = new Object[1];
            try {
                declaredField.setAccessible(true);
                objects[0] = declaredField.get(protocolEntity);
            } catch (IllegalAccessException e) {
                throw new ProtocolConfigException("非数组", e);
            }
        }
        for (Object object : objects) {
            add(new DataProtocolPacketElement(object, typeConvert));
        }
    }

    public DataProtocolPacketElementList(ByteBuffer byteBuffer, Class<? extends TypeConvert> type, DataProtocolIndexCode code, Short elementSize) {
        this(elementSize);
        for (int i = 0; i < elementSize; i++) {
            add(new DataProtocolPacketElement(byteBuffer, type, code));
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
