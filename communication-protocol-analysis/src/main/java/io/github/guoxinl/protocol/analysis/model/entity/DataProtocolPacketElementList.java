package io.github.guoxinl.protocol.analysis.model.entity;

import io.github.guoxinl.protocol.analysis.conf.convert.TypeConvert;
import io.github.guoxinl.protocol.analysis.utils.ClassUtils;
import io.netty.buffer.ByteBuf;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
            Object field = ClassUtils.getFieldValue(protocolEntity, declaredField);
            //iterable = Arrays.asList((Object[])object);
            //以上方式无法对基本数据类型数组进行转换，
            objects = IntStream.range(0, Array.getLength(field)).mapToObj(i -> Array.get(field, i)).toArray();
        } else {
            objects = new Object[]{ClassUtils.getFieldValue(protocolEntity, declaredField)};
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

    public Object protocolEntity(Class<?> type) {
        if (size() == 1) {
            DataProtocolPacketElement dataProtocolPacketElement = this.get(0);
            return dataProtocolPacketElement.getData();
        } else {
            type = type.isArray() ? type.getComponentType() : type;
            Object array = Array.newInstance(type, this.size());
            for (int i = 0; i < this.size(); i++) {
                Array.set(array, i, this.get(i).getData());
            }
            return array;
        }
    }
}
