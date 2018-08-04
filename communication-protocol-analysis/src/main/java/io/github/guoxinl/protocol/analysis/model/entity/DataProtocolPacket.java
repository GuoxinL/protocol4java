package io.github.guoxinl.protocol.analysis.model.entity;

import io.github.guoxinl.protocol.analysis.conf.cache.TypeCache;
import io.github.guoxinl.protocol.analysis.conf.cache.TypeIndexCache;
import io.github.guoxinl.protocol.analysis.conf.convert.DefaultTypeClass;
import io.github.guoxinl.protocol.analysis.conf.convert.TypeConvert;
import io.github.guoxinl.protocol.analysis.model.anno.TypeIndex;
import io.github.guoxinl.protocol.analysis.model.constants.ProtocolSupportType;
import io.github.guoxinl.protocol.analysis.model.exception.ProtocolConfigException;
import io.github.guoxinl.protocol.analysis.model.exception.ProtocolException;
import io.github.guoxinl.protocol.analysis.model.exception.ProtocolTypeNotSupportException;
import io.github.guoxinl.protocol.analysis.model.exception.TypeCacheNotFoundException;
import io.github.guoxinl.protocol.analysis.utils.ClassUtils;
import io.netty.buffer.ByteBuf;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
class DataProtocolPacket implements ProtocolSerialization {

    /**
     * 类型
     */
    private DataProtocolIndexType         type;
    /**
     * 元素集合
     */
    private DataProtocolPacketElementList elements;
    /**
     * 利用hash排序
     */
    private int                           hash;
    /**
     * 字段索引
     */
    private short                         codeIndex;

    /**
     * 解析数据
     *
     * @param byteBuf 字节流
     */
    DataProtocolPacket(ByteBuf byteBuf) {
        {
            this.codeIndex = byteBuf.readUnsignedByte();
//            this.code = DataProtocolIndexCode.create(codeIndex);
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
        // 使用hashCode排序后下标代替code
        this.hash = declaredField.getName().toLowerCase().hashCode();

        TypeIndex           typeIndexAnnotation       = declaredField.getAnnotation(TypeIndex.class);
        boolean             typeIndexAnnotationExists = Objects.isNull(typeIndexAnnotation);
        short               typeIndex;
        ProtocolSupportType protocolSupportType;
        // 如果注解不存在，按照默认类型处理
            Class<?> type;
            Type     genericType = declaredField.getGenericType();
            if (ClassUtils.instanceOfClass(genericType)) {

                Class clazz = (Class) genericType;
                if (clazz.isArray()) {
                    type = clazz.getComponentType();
                    // 设置类型
                    protocolSupportType = ProtocolSupportType.Array;
                } else {
                    type = clazz;
                    // 设置类型
                    protocolSupportType = ProtocolSupportType.PrimitiveClass;
                }

            } else if (ClassUtils.instanceOfParameterizedType(genericType)) {
                // 表示一种参数化的类型，比如Collection, Map
                // 目前只支持实现于Collection的集合类型，泛型参数不能为空
                ParameterizedType parameterizedType   = (ParameterizedType) genericType;
                Type[]            actualTypeArguments = parameterizedType.getActualTypeArguments();
                // 不支持没有泛型参数或者多个泛型参数
                if (actualTypeArguments.length != 1) {
                    throw new ProtocolTypeNotSupportException("Protocol type not support!");
                }
                // 判断泛型中的类型是否是Class
                if (ClassUtils.instanceOfClass(actualTypeArguments[0])) {
                    Class actualTypeArguments0 = (Class) actualTypeArguments[0];
                    // 判断是否是数组
                    if (actualTypeArguments0.isArray()) {
                        throw new ProtocolTypeNotSupportException("不支持泛型类型中使用数组类型");
                    }
                    // 不是数组则获取类型
                    type = actualTypeArguments0;
                    // 设置类型
                    protocolSupportType = ProtocolSupportType.GenericTypeCollection;
                } else {
                    throw new ProtocolTypeNotSupportException("不支持泛型类型中使用非协议基本类型！");
                }
            } else if (ClassUtils.instanceOfGenericArrayType(genericType)) {
                throw new ProtocolTypeNotSupportException("Protocol type not support!");
            } else if (ClassUtils.instanceOfTypeVariable(genericType)) {
                throw new ProtocolTypeNotSupportException("Protocol type not support!");
            } else {
                throw new ProtocolTypeNotSupportException("Protocol type not support!");
            }
            typeIndex = DefaultTypeClass.findTypeIndexByClass(type);
//        } else {
//            typeIndex = TypeConvert.getTypeIndex(typeIndexAnnotation.convert());
//            protocolSupportType = ProtocolSupportType.Array;
//        }

        TypeCache typeCache = TypeIndexCache.getInstance().get(typeIndex);
        if (Objects.isNull(typeCache)) {
            throw new ProtocolConfigException("TypeIndex为 " + typeIndex + "TypeConvert未注册");
        }
        Class<? extends TypeConvert> typeConvert = typeCache.getTypeConvert();

        this.type = DataProtocolIndexType.create(typeIndex, typeConvert);
        if (Objects.nonNull(protocolEntity)) {
            this.elements = new DataProtocolPacketElementList(declaredField, protocolEntity, typeConvert, protocolSupportType);
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

    void protocolEntity(Object instance, int hash, Field declaredField) {
        if (this.hash == hash) {
            declaredField.setAccessible(true);
            try {
                declaredField.set(instance, /*packet.getData()*/null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                // 如果这个对象正在执行Java语言访问控制，并且底层子弹不可访问会出现此错误
                throw new ProtocolException("如果这个对象正在执行Java语言访问控制 ，并且底层子弹不可访问会出现此错误", e);
            }
        }
    }
}
