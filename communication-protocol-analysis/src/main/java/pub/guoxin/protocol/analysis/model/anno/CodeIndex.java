package pub.guoxin.protocol.analysis.model.anno;

import java.lang.annotation.*;

/**
 * 数据段字段 - 用于标记数据段字段
 * <p>
 * Created by guoxin on 18-6-13.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeIndex {
    /**
     * 索引
     * <p>
     * 同时该字段也表示该字段在传输时的顺序，顺序从小到大正序排列
     */
    short index();

    /**
     * 描述
     */
    String description() default "";
}
