package io.github.guoxinl.protocol.analysis.model.entity;

import io.netty.buffer.ByteBuf;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import io.github.guoxinl.protocol.analysis.model.anno.CodeIndex;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 协议：数据段集合
 * 其中包含了所有的数据段
 * <p>
 * Create by guoxin on 2018/7/8
 */
@Slf4j
@EqualsAndHashCode
@NoArgsConstructor
class DataProtocolPacketList extends ArrayList<DataProtocolPacket> implements ProtocolSerialization {

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
        Field[] declaredFields = clazz.getDeclaredFields();

        // 拼凑数据段
        for (Field declaredField : declaredFields) {
            add(new DataProtocolPacket(declaredField, protocolEntity));
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

    public void protocolEntity(Object instance, Field[] declaredFields) {
        for (Field declaredField : declaredFields) {
            int hash = declaredField.getName().toLowerCase().hashCode();
            for (DataProtocolPacket packet : this) {
                packet.protocolEntity(instance, hash, declaredField);
            }
        }
    }
}
