package io.github.guoxinl.protocol.analysis.model.entity;

import io.netty.buffer.ByteBuf;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import io.github.guoxinl.protocol.analysis.conf.convert.TypeConvert;
import io.github.guoxinl.protocol.analysis.utils.ClassUtils;

import java.io.Serializable;

/**
 * 协议：数据段 - 元素
 * Create by guoxin on 2018/7/8
 */
@Slf4j
@Getter
@ToString(exclude = "typeClass")
@EqualsAndHashCode
@NoArgsConstructor
class DataProtocolPacketElement implements Serializable, ProtocolSerialization {

    /**
     * 元素长度
     */
    private int                          elementLength;
    /**
     * 元素数据
     */
    private Object                       data;
    /**
     * 该变量不参与Element序列化
     */
    private Class<? extends TypeConvert> typeClass;

    DataProtocolPacketElement(Object object, Class<? extends TypeConvert> typeConvert) {
        this.data = object;
        this.typeClass = typeConvert;
    }

    DataProtocolPacketElement(ByteBuf byteBuf, Class<? extends TypeConvert> type, DataProtocolIndexCode code) {
        this.typeClass = type;
        {
            // 解析长度
            this.elementLength = byteBuf.readUnsignedShort();
            log.debug("elementLength readerIndex:{}", byteBuf.readerIndex());
        }
        {
            ByteBuf elementByteBuf = byteBuf.readBytes(this.elementLength);
            log.debug("elementData readerIndex:{}", byteBuf.readerIndex());
            // 解析数据
            // T decode(byte[] bytes);
            Object data = ClassUtils.methodInvoke(this.typeClass, "decode", ByteBuf.class, elementByteBuf);
            this.data = data;
        }
    }

    @Override
    public void serialization(ByteBuf byteBuf) {
        Object  data        = ClassUtils.methodInvoke(this.typeClass, "encode", Object.class, this.data);
        ByteBuf dataByteBuf = (ByteBuf) data;

        // 先写入长度
        this.elementLength = dataByteBuf.writerIndex();
        byteBuf.writeShort(this.elementLength);
        log.debug("elementLength writerIndex:{}", byteBuf.writerIndex());

        // 在写入数据
        byteBuf.writeBytes(dataByteBuf);
        log.debug("elementData writerIndex:{}", byteBuf.writerIndex());

    }

}
