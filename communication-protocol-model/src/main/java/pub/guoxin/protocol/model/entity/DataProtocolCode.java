package pub.guoxin.protocol.model.entity;

import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Create by guoxin on 2018/6/12
 */
@NoArgsConstructor
public class DataProtocolCode extends BaseDataProtocol<String> implements Serializable {
    public DataProtocolCode(int index, String description) {
        super(index, description);
    }

    public static DataProtocolCode create(int index, String description){
        return new DataProtocolCode(index, description);
    }
}
