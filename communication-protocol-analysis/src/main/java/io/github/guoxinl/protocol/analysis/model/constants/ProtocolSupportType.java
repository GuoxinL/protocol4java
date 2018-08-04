package io.github.guoxinl.protocol.analysis.model.constants;

import io.github.guoxinl.protocol.analysis.conf.convert.DefaultTypeClass;

/**
 * 协议所支持的类型
 * <p>
 * Create by guoxin on 2018/8/4
 */
public enum ProtocolSupportType {
    /**
     * 协议基本类型，并非java 基本类型
     * <p>
     * See: {@link DefaultTypeClass}
     */
    PrimitiveClass,
    /**
     * 数组类型
     */
    Array,
    /**
     * 泛型集合类型
     */
    GenericTypeCollection
}
