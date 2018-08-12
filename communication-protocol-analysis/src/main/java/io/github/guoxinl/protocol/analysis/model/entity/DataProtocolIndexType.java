package io.github.guoxinl.protocol.analysis.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import io.github.guoxinl.protocol.analysis.conf.convert.TypeConvert;

import java.io.Serializable;

/**
 * 协议：类型索引
 * Create by guoxin on 2018/6/12
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DataProtocolIndexType extends BaseDataProtocolIndex<String> {

    private Class<? extends TypeConvert> type;

    private DataProtocolIndexType(int index, String value, Class<? extends TypeConvert> type) {
        super(index, value);
        this.type = type;
    }

    public static DataProtocolIndexType create(int index, String value, Class<? extends TypeConvert> type) {
        return new DataProtocolIndexType(index, value, type);
    }

    public static DataProtocolIndexType create(int index, Class<? extends TypeConvert> type) {
        return create(index, null, type);
    }
}
