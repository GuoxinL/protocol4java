package pub.guoxin.protocol.analysis.model.entity;

import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 协议 命令
 * Create by guoxin on 2018/6/12
 */
@NoArgsConstructor
public class DataProtocolCommand extends BaseDataProtocol<String> implements Serializable {

    public DataProtocolCommand(short index, String description) {
        super(index, description);
    }

    public static DataProtocolCommand create(short index, String description) {
        return new DataProtocolCommand(index, description);
    }
}
