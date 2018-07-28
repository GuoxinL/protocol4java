package io.github.protocol.analysis.conf.register;

import lombok.Getter;
import io.github.protocol.analysis.conf.cache.DataProtocolCache;
import io.github.protocol.analysis.conf.entity.ProtocolSet;
import io.github.protocol.analysis.model.entity.DataProtocol;
import io.github.protocol.analysis.model.entity.ProtocolEntity;
import io.github.protocol.analysis.model.exception.ProtocolConfigException;

/**
 * 数据码索引表(code), 枚举类须实现该类
 * <p>
 * Created by guoxin on 18-2-25.
 */
@Getter
public class ProtocolEntityRegister implements ProtocolRegister<Class<? extends ProtocolEntity>> {

    private ProtocolSet<Class<? extends ProtocolEntity>> register = ProtocolSet.newDataProtocolMap();

    @Override
    public ProtocolRegister register(Class<? extends ProtocolEntity> protocolEntity) {
        DataProtocol             analysis = DataProtocol.convert(protocolEntity);

        if (DataProtocolCache.getInstance().exists(analysis.getHeader().getProtocolKey())) {
            throw new ProtocolConfigException("Command index exists, " + analysis.getHeader().getProtocolKey());
        }
        DataProtocolCache.getInstance().put(analysis.getHeader().getProtocolKey(), analysis);
        return this;
    }

}
