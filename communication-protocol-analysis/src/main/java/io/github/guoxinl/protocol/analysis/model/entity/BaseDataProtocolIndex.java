package io.github.guoxinl.protocol.analysis.model.entity;

import lombok.*;

/**
 * 协议对象：索引
 * <p>
 * Create by guoxin on 2018/6/12
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "value")
@NoArgsConstructor
@AllArgsConstructor
abstract class BaseDataProtocolIndex<V> {
    private int index;
    private V   value;
}
