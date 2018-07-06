package pub.guoxin.protocol.analysis.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Create by guoxin on 2018/6/12
 */
@Getter
@NoArgsConstructor
public class DataProtocolType extends BaseDataProtocol<Class<?>> implements Serializable {

    public DataProtocolType(short index, Class<?> value) {
        super(index, value);
    }

    public static DataProtocolType create(short index, Class<?> value) {
        return new DataProtocolType(index, value);
    }
}
