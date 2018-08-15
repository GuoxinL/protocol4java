package io.github.guoxinl.protocol.analysis.model.entity;

import io.github.guoxinl.protocol.analysis.conf.cache.TypeCache;
import io.github.guoxinl.protocol.analysis.conf.cache.TypeIndexCache;
import io.github.guoxinl.protocol.analysis.conf.convert.DefaultTypeClass;
import io.github.guoxinl.protocol.analysis.conf.convert.TypeConvert;
import io.github.guoxinl.protocol.analysis.model.anno.TypeIndex;
import io.github.guoxinl.protocol.analysis.model.exception.ProtocolConfigException;
import io.github.guoxinl.protocol.analysis.model.exception.TypeCacheNotFoundException;
import io.github.guoxinl.protocol.analysis.utils.ClassUtils;
import io.netty.buffer.ByteBuf;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Objects;

//import io.github.guoxinl.protocol.analysis.model.anno.CodeIndex;

/**
 * 协议：数据段
 * <p>
 * Created by guoxin on 18-2-25.
 */
@Slf4j
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
class DataProtocolPacket implements ProtocolSerialization {
    /**
     * 利用hash排序
     */
    private int                           hash;
    /**
     * 字段索引
     */
    private short                         codeIndex;
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
     * @param byteBuf            字节流
     * @param dataProtocolPacket 缓存中的数据段
     */
    DataProtocolPacket(ByteBuf byteBuf, DataProtocolPacketList dataProtocolPacket) {
        {
            this.codeIndex = byteBuf.readUnsignedByte();
            hash = dataProtocolPacket.stream().filter(protocolPacket -> this.codeIndex == protocolPacket.codeIndex).findFirst().map(protocolPacket -> protocolPacket.hash).orElse(this.hash);
            log.debug("codeIndex readerIndex:{}", byteBuf.readerIndex());
        }
        {
            short typeIndex = byteBuf.readUnsignedByte();
            log.debug("typeIndex readerIndex:{}", byteBuf.readerIndex());

            TypeCache typeCache = TypeIndexCache.getInstance().get(typeIndex);
            if (Objects.isNull(typeCache)) {
                throw new TypeCacheNotFoundException("typeIndex " + typeIndex + ", TypeConvert Not found!");
            }
            Class<? extends TypeConvert> typeConvert = typeCache.getTypeConvert();
            this.type = DataProtocolIndexType.create(typeIndex, typeConvert);
        }
        {
            this.elements = new DataProtocolPacketElementList(byteBuf, this.type.getType());
        }
    }

    DataProtocolPacket(Field declaredField, ProtocolEntity protocolEntity) {
        this.hash = declaredField.getName().toLowerCase().hashCode();

        TypeIndex typeIndexAnnotation = declaredField.getAnnotation(TypeIndex.class);
        short     typeIndex;
        if (Objects.isNull(typeIndexAnnotation)) {
            Class<?> type;
            if (declaredField.getType().isArray()) {
                type = declaredField.getType().getComponentType();
            } else {
                type = declaredField.getType();
            }

            typeIndex = DefaultTypeClass.findTypeIndexByClass(type);
            // 由于存在默认类型概念删除异常
            // throw new ProtocolConfigException("字段" + declaredField.getName() + "请使用 @TypeIndex 注解对协议对象进行标注");
        } else {
            typeIndex = TypeConvert.getTypeIndex(typeIndexAnnotation.convert());
        }

        TypeCache typeCache = TypeIndexCache.getInstance().get(typeIndex);
        if (Objects.isNull(typeCache)) {
            throw new ProtocolConfigException("TypeIndex为 " + typeIndex + "TypeConvert未注册");
        }
        Class<? extends TypeConvert> typeConvert = typeCache.getTypeConvert();

        this.type = DataProtocolIndexType.create(typeIndex, typeConvert);
        if (Objects.nonNull(protocolEntity)) {
            this.elements = new DataProtocolPacketElementList(declaredField, protocolEntity, typeConvert);
        }
    }

    @Override
    public void serialization(ByteBuf byteBuf) {
        {
            byteBuf.writeByte(this.codeIndex);
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

    boolean protocolEntity(Object instance, int hash, Field declaredField) {
        if (this.hash != hash) {
            log.info("this.hash: {} not equal hash: {}", this.hash, hash);
            return false;
        }
        Object o = this.elements.protocolEntity(declaredField.getType());
        ClassUtils.setFieldValue(instance, declaredField, o);
        return true;
    }
}
