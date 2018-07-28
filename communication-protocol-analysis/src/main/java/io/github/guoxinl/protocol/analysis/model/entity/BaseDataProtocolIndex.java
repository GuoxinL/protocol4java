package io.github.guoxinl.protocol.analysis.model.entity;

import lombok.*;

import java.util.Objects;

/**
 * 协议对象：索引
 * <p>
 * Create by guoxin on 2018/6/12
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class BaseDataProtocolIndex<V> {
    private int index;
    private V   value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDataProtocolIndex<?> that = (BaseDataProtocolIndex<?>) o;
        return index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
