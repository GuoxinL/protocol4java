package pub.guoxin.protocol.analysis.model.entity;

import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 协议：字段索引
 * Create by guoxin on 2018/6/12
 */
@NoArgsConstructor
public class DataProtocolIndexCode extends BaseDataProtocolIndex<String> implements Serializable {

    public DataProtocolIndexCode(short index, String description) {
        super(index, description);
    }

    public static DataProtocolIndexCode create(short index, String description) {
        return new DataProtocolIndexCode(index, description);
    }

    public static DataProtocolIndexCode create(short codeIndex) {
        return create(codeIndex, null);
    }
}
