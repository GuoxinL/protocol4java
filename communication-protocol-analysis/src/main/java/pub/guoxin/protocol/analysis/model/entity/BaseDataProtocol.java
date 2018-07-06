package pub.guoxin.protocol.analysis.model.entity;

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
    private short index;
    private V   value;
}
