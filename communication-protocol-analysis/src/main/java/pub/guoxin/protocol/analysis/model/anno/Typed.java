package pub.guoxin.protocol.analysis.model.anno;

import java.lang.annotation.*;

/**
 * 用于注解 {@link pub.guoxin.protocol.analysis.conf.convert.TypeConvert} 的实现类
 * eg:
 * {@link pub.guoxin.protocol.analysis.conf.convert.SignedChar2byteTypeConvert}
 * {@link pub.guoxin.protocol.analysis.conf.convert.SignedShort2shortTypeConvert}
 * {@link pub.guoxin.protocol.analysis.conf.convert.SignedInt2integerTypeConvert}
 * {@link pub.guoxin.protocol.analysis.conf.convert.SignedLongLong2longTypeConvert}
 * {@link pub.guoxin.protocol.analysis.conf.convert.FloatTypeConvert}
 * {@link pub.guoxin.protocol.analysis.conf.convert.DoubleTypeConvert}
 * {@link pub.guoxin.protocol.analysis.conf.convert.BooleanTypeConvert}
 * {@link pub.guoxin.protocol.analysis.conf.convert.StringTypeConvert}
 * <p>
 * {@link pub.guoxin.protocol.analysis.conf.convert.UnsignedChar2byteTypeConvert}
 * {@link pub.guoxin.protocol.analysis.conf.convert.UnsignedShort2shortTypeConvert}
 * {@link pub.guoxin.protocol.analysis.conf.convert.UnsignedInt2integerTypeConvert}
 * {@link pub.guoxin.protocol.analysis.conf.convert.UnsignedLongLong2longTypeConvert}
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
    short index();

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
