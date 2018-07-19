package pub.guoxin.protocol.analysis.model.entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 协议：命令索引
 * Create by guoxin on 2018/6/12
 */
@ToString
@NoArgsConstructor
public class DataProtocolIndexCommand extends BaseDataProtocolIndex<String> implements Serializable {

    public DataProtocolIndexCommand(short index, String description) {
        super(index, description);
    }

    public static DataProtocolIndexCommand create(short index, String description) {
        return new DataProtocolIndexCommand(index, description);
    }
}
