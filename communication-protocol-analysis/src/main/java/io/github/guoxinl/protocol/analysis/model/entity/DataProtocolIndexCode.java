package io.github.guoxinl.protocol.analysis.model.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 协议：字段索引
 * Create by guoxin on 2018/6/12
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DataProtocolIndexCode extends BaseDataProtocolIndex<String> implements Serializable {

    private DataProtocolIndexCode(int index, String description) {
        super(index, description);
    }

    public static DataProtocolIndexCode create(int codeIndex, String description) {
        return new DataProtocolIndexCode(codeIndex, description);
    }

    public static DataProtocolIndexCode create(int codeIndex) {
        return create(codeIndex, null);
    }
}
