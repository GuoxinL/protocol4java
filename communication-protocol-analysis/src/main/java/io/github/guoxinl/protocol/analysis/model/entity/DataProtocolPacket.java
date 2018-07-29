package io.github.guoxinl.protocol.analysis.model.entity;

import io.github.guoxinl.protocol.analysis.model.exception.ProtocolConfigException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import io.github.guoxinl.protocol.analysis.conf.cache.TypeCache;
import io.github.guoxinl.protocol.analysis.conf.cache.TypeIndexCache;
import io.github.guoxinl.protocol.analysis.conf.convert.TypeConvert;
import io.github.guoxinl.protocol.analysis.model.anno.CodeIndex;
import io.github.guoxinl.protocol.analysis.model.anno.TypeIndex;
import io.github.guoxinl.protocol.analysis.model.exception.ProtocolException;
import io.github.guoxinl.protocol.analysis.model.exception.TypeCacheNotFoundException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

/**
 * 协议：数据段
 * <p>
 * Created by guoxin on 18-2-25.
 */
@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class DataProtocolPacket implements Serializable, ProtocolSerialization {
    /**
     * 字段
     */
    private DataProtocolIndexCode         code;
    /**
     * 类型
     */
    private DataProtocolIndexType         type;
    /**
     * 元素集合
     */
    private DataProtocolPacketElementList elements;

    /**
     * 解析数据
     *
     * @param byteBuf 字节流
     */
    DataProtocolPacket(ByteBuf byteBuf) {
        {
            int codeIndex = byteBuf.readUnsignedByte();
            this.code = DataProtocolIndexCode.create(codeIndex);
            log.debug("codeIndex readerIndex:{}", byteBuf.readerIndex());
        }
        {
            int       typeIndex = byteBuf.readUnsignedByte();
            log.debug("typeIndex readerIndex:{}", byteBuf.readerIndex());

            TypeCache typeCache = TypeIndexCache.getInstance().get(typeIndex);
            if (Objects.isNull(typeCache)) {
                throw new TypeCacheNotFoundException("typeIndex " + typeIndex + ", TypeConvert Not found!");
            }
            Class<? extends TypeConvert> typeConvert = typeCache.getTypeConvert();
            this.type = DataProtocolIndexType.create(typeIndex, typeConvert);
        }
        {
            this.elements = new DataProtocolPacketElementList(byteBuf, this.type.getType(), this.code);
        }
    }

    DataProtocolPacket(Field declaredField, ProtocolEntity protocolEntity) {
        CodeIndex codeIndexAnnotation = declaredField.getAnnotation(CodeIndex.class);
        TypeIndex typeIndexAnnotation = declaredField.getAnnotation(TypeIndex.class);
        if (Objects.isNull(codeIndexAnnotation)) {
            throw new ProtocolConfigException("字段" + declaredField.getName() + "请使用 @CodeIndex 注解对协议对象进行标注");
        }
        if (Objects.isNull(typeIndexAnnotation)) {
            throw new ProtocolConfigException("字段" + declaredField.getName() + "请使用 @TypeIndex 注解对协议对象进行标注");
        }
        this.code = DataProtocolIndexCode.create(codeIndexAnnotation.index(), codeIndexAnnotation.description());

        int                          typeIndex   = TypeConvert.getTypeIndex(typeIndexAnnotation.convert());
        TypeCache                    typeCache   = TypeIndexCache.getInstance().get(typeIndex);
        Class<? extends TypeConvert> typeConvert = typeCache.getTypeConvert();

        this.type = DataProtocolIndexType.create(typeIndex, typeConvert);
        if (Objects.nonNull(protocolEntity)) {
            this.elements = new DataProtocolPacketElementList(declaredField, protocolEntity, typeConvert);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataProtocolPacket that = (DataProtocolPacket) o;
        return Objects.equals(code, that.code) && Objects.equals(type, that.type) && Objects.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, type, elements);
    }

    @Override
    public void serialization(ByteBuf byteBuf) {
        {
            byteBuf.writeByte(this.code.getIndex());
            log.debug("code writerIndex: {}", byteBuf.writerIndex());
        }
        {
            byteBuf.writeByte(this.type.getIndex());
            log.debug("type writerIndex: {}", byteBuf.writerIndex());
        }
        {
            elements.serialization(byteBuf);
            log.debug("elements writerIndex: {}", byteBuf.writerIndex());
        }
    }

    void protocolEntity(Object instance, short codeIndex, Field declaredField) {
        if (codeIndex == this.getCode().getIndex()) {
            declaredField.setAccessible(true);
            try {
                declaredField.set(instance, /*packet.getData()*/null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                // TODO 如果这个对象正在执行Java语言访问控制，并且底层子弹不可访问会出现此错误
                throw new ProtocolException("如果这个对象正在执行Java语言访问控制 ，并且底层子弹不可访问会出现此错误", e);
            }
        }
    }
}
