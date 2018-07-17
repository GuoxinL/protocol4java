package pub.guoxin.protocol.analysis.conf.convert;

import pub.guoxin.protocol.analysis.conf.cache.TypeCache;
import pub.guoxin.protocol.analysis.model.anno.Typed;
import pub.guoxin.protocol.analysis.utils.ClassUtils;

/**
 * Create by guoxin on 2018/6/26
 */
public interface TypeConvert<T> {
    int BIT = 8;

    /**
     * 将{@code t}解码为字节流
     *
     * @param t obj
     * @return 字节流
     */
    byte[] encode(T t);

    /**
     * 将字节流编码为{@link T}
     *
     * @param bytes 字节流
     * @return obj
     */
    T decode(byte[] bytes);

    static short getTypeIndex(Class<?> clazz) {
        return clazz.getAnnotation(Typed.class).index();
    }

    static TypeCache getTypeCache(Class<? extends TypeConvert> typeConvert, short index) {
        Class<?>  genericsType = ClassUtils.getGenericsType(typeConvert);
        TypeCache typeCache    = TypeCache.builder().index(index).typeClass(genericsType).typeConvert(typeConvert).build();
        return typeCache;
    }
}
