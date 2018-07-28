package io.github.guoxinl.protocol.analysis.model.entity;

import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import io.github.guoxinl.protocol.analysis.model.anno.CodeIndex;
import io.github.guoxinl.protocol.analysis.model.anno.TypeIndex;
import io.github.guoxinl.protocol.analysis.model.exception.ProtocolConfigException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

/**
 * 协议：数据段集合
 * 其中包含了所有的数据段
 * <p>
 * Create by guoxin on 2018/7/8
 */
@Slf4j
class DataProtocolPacketList extends ArrayList<DataProtocolPacket> implements Serializable, ProtocolSerialization {

    private int totalPacket;

    private DataProtocolPacketList(int initialCapacity) {
        super(initialCapacity);
        this.totalPacket = initialCapacity;
    }

    /**
     * 解析数据
     *
     * @param byteBuf 字节流
     */
    DataProtocolPacketList(ByteBuf byteBuf) {
        {
            this.totalPacket = byteBuf.readUnsignedShort();
            log.debug("totalPacket readerIndex: {}", byteBuf.readerIndex());
        }
        for (short i = 0; i < totalPacket; i++) {
            add(new DataProtocolPacket(byteBuf));
        }
    }

    /**
     * 加载协议对象
     *
     * @param clazz          协议对象class
     * @param protocolEntity
     */
    DataProtocolPacketList(Class<? extends ProtocolEntity> clazz, ProtocolEntity protocolEntity) {
        // 拼凑数据段
        for (Field declaredField : clazz.getDeclaredFields()) {
            CodeIndex codeIndexAnnotation = declaredField.getAnnotation(CodeIndex.class);
            TypeIndex typeIndexAnnotation = declaredField.getAnnotation(TypeIndex.class);
            if (Objects.isNull(codeIndexAnnotation)) {
                throw new ProtocolConfigException("字段" + declaredField.getName() + "请使用 @CodeIndex 注解对协议对象进行标注");
            }
            if (Objects.isNull(typeIndexAnnotation)) {
                throw new ProtocolConfigException("字段" + declaredField.getName() + "请使用 @TypeIndex 注解对协议对象进行标注");
            }
            add(new DataProtocolPacket(declaredField, codeIndexAnnotation, typeIndexAnnotation, protocolEntity));
        }
        this.totalPacket = this.size();
    }

    @Override
    public void serialization(ByteBuf byteBuf) {
        {
            // 协议 数据段总包数
            byteBuf.writeShort(this.totalPacket);
            log.debug("totalPacket writerIndex: {}", byteBuf.writerIndex());
        }
        {
            for (DataProtocolPacket dataProtocolPacket : this) {
                dataProtocolPacket.serialization(byteBuf);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DataProtocolPacketList that = (DataProtocolPacketList) o;
        return totalPacket == that.totalPacket;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), totalPacket);
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
