package io.github.guoxinl.protocol.analysis.model.entity;

import io.netty.buffer.ByteBuf;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.github.guoxinl.protocol.analysis.conf.convert.TypeConvert;
import io.github.guoxinl.protocol.analysis.model.exception.ProtocolConfigException;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * 协议：数据段 - 元素集合
 * Create by guoxin on 2018/7/8
 */
@Slf4j
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
class DataProtocolPacketElementList extends ArrayList<DataProtocolPacketElement> implements ProtocolSerialization {

    /**
     * 元素数量
     */
    private int elementSize;

    DataProtocolPacketElementList(ByteBuf byteBuf, Class<? extends TypeConvert> type) {
        {
            this.elementSize = byteBuf.readUnsignedShort();
            log.debug("elementSize readerIndex:{}", byteBuf.readerIndex());
        }
        {
            for (int i = 0; i < this.elementSize; i++) {
                add(new DataProtocolPacketElement(byteBuf, type));
            }
        }
    }

    DataProtocolPacketElementList(Field declaredField, ProtocolEntity protocolEntity, Class<? extends TypeConvert> typeConvert) {
        Object[] objects;
        if (declaredField.getType().isArray()) {
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
        this.elementSize = this.size();
    }

    @Override
    public void serialization(ByteBuf byteBuf) {
        {
            byteBuf.writeShort(this.elementSize);
            log.debug("elementSize writerIndex: {}", byteBuf.writerIndex());
        }
        {
            for (DataProtocolPacketElement dataProtocolPacketElement : this) {
                dataProtocolPacketElement.serialization(byteBuf);
            }
        }
    }
}
