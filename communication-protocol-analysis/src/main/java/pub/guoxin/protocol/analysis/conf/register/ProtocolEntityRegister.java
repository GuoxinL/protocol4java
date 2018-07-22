package pub.guoxin.protocol.analysis.conf.register;

import lombok.Getter;
import pub.guoxin.protocol.analysis.conf.cache.DataProtocolCache;
import pub.guoxin.protocol.analysis.conf.entity.ProtocolSet;
import pub.guoxin.protocol.analysis.model.entity.DataProtocol;
import pub.guoxin.protocol.analysis.model.entity.DataProtocolIndexCommand;
import pub.guoxin.protocol.analysis.model.entity.ProtocolEntity;
import pub.guoxin.protocol.analysis.model.exception.ProtocolConfigException;

/**
 * 数据码索引表(code), 枚举类须实现该类
 * <p>
 * Created by guoxin on 18-2-25.
 */
@Getter
public class ProtocolEntityRegister implements ProtocolRegister<Class<? extends ProtocolEntity>> {

    private ProtocolSet<Class<? extends ProtocolEntity>> register = ProtocolSet.newDataProtocolMap();

    @Override
    public ProtocolRegister register(Class<? extends ProtocolEntity> element) {
        DataProtocol             analysis = DataProtocol.analysis(element);
        DataProtocolIndexCommand command  = analysis.getHeader().getCommand();
        if (DataProtocolCache.getInstance().exists(command.getIndex())) {
            throw new ProtocolConfigException("Command index exists, " + command.toString());
        }
        DataProtocolCache.getInstance().put(command.getIndex(), analysis);
        return this;
    }

}
