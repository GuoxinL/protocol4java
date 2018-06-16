package pub.guoxin.protocol.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Create by guoxin on 2018/6/12
 */
@Getter
@NoArgsConstructor
public class DataProtocolType extends BaseDataProtocol<Class<?>> implements Serializable {

    private String description;

    public DataProtocolType(short index, Class<?> value, String description) {
        super(index, value);
        this.description = description;
    }

    public static DataProtocolType create(short index, Class<?> value, String description) {
        return new DataProtocolType(index, value, description);
    }
}
