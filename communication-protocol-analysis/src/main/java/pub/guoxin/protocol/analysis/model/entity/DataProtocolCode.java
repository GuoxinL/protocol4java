package pub.guoxin.protocol.analysis.model.entity;

import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Create by guoxin on 2018/6/12
 */
@NoArgsConstructor
public class DataProtocolCode extends BaseDataProtocol<String> implements Serializable {

    public DataProtocolCode(short index, String description) {
        super(index, description);
    }

    public static DataProtocolCode create(short index, String description) {
        return new DataProtocolCode(index, description);
    }
}
