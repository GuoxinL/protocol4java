package pub.guoxin.protocol.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Create by guoxin on 2018/6/12
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDataProtocol<V> {
    private int index;
    private V   value;
}
