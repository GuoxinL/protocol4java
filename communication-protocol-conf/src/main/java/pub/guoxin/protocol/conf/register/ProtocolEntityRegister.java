package pub.guoxin.protocol.conf.register;

import lombok.Getter;
import pub.guoxin.protocol.model.entity.ProtocolSet;

/**
 * 数据码索引表(code), 枚举类须实现该类
 * <p>
 * Created by guoxin on 18-2-25.
 */
@Getter
public class ProtocolEntityRegister implements ProtocolRegister<Class<?>> {

    private ProtocolSet<Class<?>> register = ProtocolSet.newDataProtocolMap();

    @Override
    public ProtocolRegister register(Class<?> element) {
        register.register(element);
        return this;
    }
}
