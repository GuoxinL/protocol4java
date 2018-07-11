package pub.guoxin.protocol.analysis.model.entity;

import pub.guoxin.protocol.analysis.model.anno.CodeIndex;
import pub.guoxin.protocol.analysis.model.constants.DataProtocolConstants;
import pub.guoxin.protocol.analysis.model.exception.ProtocolConfigException;
import pub.guoxin.protocol.analysis.model.exception.ProtocolException;
import pub.guoxin.protocol.analysis.utils.ArrayUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Objects;

/**
 * 协议：数据段集合
 * 其中包含了所有的数据段
 * <p>
 * Create by guoxin on 2018/7/8
 */

public class DataProtocolPacketList extends ArrayList<DataProtocolPacket> implements Serializable, ProtocolSerialization {

    public DataProtocolPacketList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * 解析数据
     *
     * @param byteBuffer  字节流
     * @param totalPacket 数据段数量
     */
    public DataProtocolPacketList(ByteBuffer byteBuffer, short totalPacket) {
        this(totalPacket);
        for (short i = 0; i < totalPacket; i++) {
            add(new DataProtocolPacket(byteBuffer));
        }
    }

    /**
     * 加载协议对象
     *
     * @param clazz          协议对象class
     * @param protocolEntity
     */
    public DataProtocolPacketList(Class<? extends ProtocolEntity> clazz, ProtocolEntity protocolEntity) {
        // 拼凑数据段
        for (Field declaredField : clazz.getDeclaredFields()) {
            CodeIndex codeIndexAnnotation = declaredField.getAnnotation(CodeIndex.class);
            if (Objects.isNull(codeIndexAnnotation)) {
                throw new ProtocolConfigException("字段" + declaredField.getName() + "请使用 @CodeIndex 注解对协议对象进行标注");
            }
            add(new DataProtocolPacket(declaredField, codeIndexAnnotation, protocolEntity));
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

    public void protocolEntity(Object instance, Field[] declaredFields) {
        for (Field declaredField : declaredFields) {
            CodeIndex codeIndexAnnotation = declaredField.getAnnotation(CodeIndex.class);
            for (DataProtocolPacket packet : this) {
                packet.protocolEntity(instance, codeIndexAnnotation.index(), declaredField);
            }
        }
    }
}
