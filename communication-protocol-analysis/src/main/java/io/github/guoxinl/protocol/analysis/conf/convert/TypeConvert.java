package io.github.guoxinl.protocol.analysis.conf.convert;

import io.github.guoxinl.protocol.analysis.conf.cache.TypeCache;
import io.github.guoxinl.protocol.analysis.model.anno.Typed;
import io.github.guoxinl.protocol.analysis.utils.ClassUtils;
import io.netty.buffer.ByteBuf;

/**
 * Create by guoxin on 2018/6/26
 */
public interface TypeConvert<T> {

    /**
     * 1 byte = 8 bit
     */
    int BIT = 8;

    static Class<?> getTypeClazz(Class<? extends TypeConvert> typeConvert){
        return ClassUtils.getGenericsType(typeConvert);
    }

    static short getTypeIndex(Class<?> clazz) {
        return clazz.getAnnotation(Typed.class).index();
    }

    static TypeCache getTypeCache(Class<? extends TypeConvert> typeConvert, int index) {
        Class<?>  genericsType = ClassUtils.getGenericsType(typeConvert);
        TypeCache typeCache    = TypeCache.builder().index(index).typeClass(genericsType).typeConvert(typeConvert).build();
        return typeCache;
    }

    /**
     * 将{@code t}解码为字节流
     *
     * @param t obj
     * @return 字节流
     */
    ByteBuf encode(T t);

    /**
     * 将字节流编码为{@link T}
     *
     * @param byteBuf 字节流
     * @return obj
     */
    T decode(ByteBuf byteBuf);
}
