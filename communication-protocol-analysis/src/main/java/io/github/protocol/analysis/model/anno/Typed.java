package io.github.protocol.analysis.model.anno;

import java.lang.annotation.*;

/**
 * 用于注解 {@link io.github.protocol.analysis.conf.convert.TypeConvert} 的实现类
 * eg:
 * {@link io.github.protocol.analysis.conf.convert.SignedChar2byteTypeConvert}
 * {@link io.github.protocol.analysis.conf.convert.SignedShort2shortTypeConvert}
 * {@link io.github.protocol.analysis.conf.convert.SignedInt2integerTypeConvert}
 * {@link io.github.protocol.analysis.conf.convert.SignedLongLong2longTypeConvert}
 * {@link io.github.protocol.analysis.conf.convert.FloatTypeConvert}
 * {@link io.github.protocol.analysis.conf.convert.DoubleTypeConvert}
 * {@link io.github.protocol.analysis.conf.convert.BooleanTypeConvert}
 * {@link io.github.protocol.analysis.conf.convert.StringTypeConvert}
 * <p>
 * {@link io.github.protocol.analysis.conf.convert.UnsignedChar2byteTypeConvert}
 * {@link io.github.protocol.analysis.conf.convert.UnsignedShort2shortTypeConvert}
 * {@link io.github.protocol.analysis.conf.convert.UnsignedInt2integerTypeConvert}
 * {@link io.github.protocol.analysis.conf.convert.UnsignedLongLong2longTypeConvert}
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
     */
    int index();

    /**
     * 字节数
     * 默认 {@link Typed#ELONGATE} 表示变长
     */
    byte numberOfBytes() default ELONGATE;

    /**
     * 描述
     */
    String description() default "TypeIndex description";

}
