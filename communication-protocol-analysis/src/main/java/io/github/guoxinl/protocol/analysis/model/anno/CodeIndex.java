package io.github.guoxinl.protocol.analysis.model.anno;

import java.lang.annotation.*;

/**
 * 数据段字段 - 用于标记数据段字段
 *
 * deprecated:
 * 用hash code进行排序代替手动标记codeIndex
 * <p>
 * Created by guoxin on 18-6-13.
 */
@Deprecated
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeIndex {
    /**
     * 索引
     * <p>
     * 同时该字段也表示该字段在传输时的顺序，顺序从小到大正序排列
     *
     * @return skip
     */
    short index();

    /**
     * 描述
     *
     * @return skip
     */
    String description();
}
