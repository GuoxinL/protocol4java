package io.github.protocol.analysis.conf.entity;

import lombok.NoArgsConstructor;
import io.github.protocol.analysis.model.entity.ProtocolEntity;

import java.util.Collection;

/**
 * 数据段 - 字段码：存放字段段索引的键值对
 * <p>
 * Created by guoxin on 18-3-11.
 */
@NoArgsConstructor
public class ProtocolEntitySet extends ProtocolSet<Class<? extends ProtocolEntity>> {
    private static final long serialVersionUID = 3774095782096499133L;

    private ProtocolEntitySet(Collection<Class<? extends ProtocolEntity>> c) {
        super(c);
    }

    public static ProtocolEntitySet newProtocolEntitySet() {
        return new ProtocolEntitySet();
    }

    public static ProtocolEntitySet toProtocolEntitySet(Collection<Class<? extends ProtocolEntity>> c) {
        return new ProtocolEntitySet(c);
    }
}
