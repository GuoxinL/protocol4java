package io.github.guoxinl.protocol.analysis.model.anno;

import io.github.guoxinl.protocol.analysis.conf.convert.*;

import java.lang.annotation.*;

/**
 * 用于注解 {@link TypeConvert} 的实现类
 * eg:
 * {@link SignedChar2byteTypeConvert}
 * {@link SignedShort2shortTypeConvert}
 * {@link SignedInt2integerTypeConvert}
 * {@link SignedLongLong2longTypeConvert}
 * {@link FloatTypeConvert}
 * {@link DoubleTypeConvert}
 * {@link BooleanTypeConvert}
 * {@link StringTypeConvert}
 * <p>
 * {@link UnsignedChar2byteTypeConvert}
 * {@link UnsignedShort2shortTypeConvert}
 * {@link UnsignedInt2integerTypeConvert}
 * {@link UnsignedLongLong2longTypeConvert}
 * <p>
 * Create by guoxin on 2018/7/12
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Typed {
    /**
     * 用于 {@link Typed#numberOfBytes()}
     */
    int ELONGATE = -1;

    /**
     * 索引
     *
     * @return skip
     */
    short index();

    /**
     * 字节数
     * 默认 {@link Typed#ELONGATE} 表示变长
     *
     * @return skip
     */
    byte numberOfBytes() default ELONGATE;

    /**
     * 描述
     *
     * @return skip
     */
    String description() default "Type description";

}
