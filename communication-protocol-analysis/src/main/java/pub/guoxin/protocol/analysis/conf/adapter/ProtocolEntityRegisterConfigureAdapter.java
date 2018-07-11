package pub.guoxin.protocol.analysis.conf.adapter;

import lombok.Getter;
import lombok.Setter;
import pub.guoxin.protocol.analysis.conf.Builder;
import pub.guoxin.protocol.analysis.conf.entity.ProtocolEntitySet;
import pub.guoxin.protocol.analysis.conf.register.ProtocolEntityRegister;

/**
 * Created by guoxin on 18-3-8.
 */
@Getter
@Setter
public abstract class ProtocolEntityRegisterConfigureAdapter implements Builder {

    private ProtocolEntitySet protocolEntitySet = ProtocolEntitySet.newProtocolEntitySet();

    public abstract void register(ProtocolEntityRegister register);

    private void register0(ProtocolEntityRegister register) {
        register(register);
        this.protocolEntitySet.addAll(register.getRegister());
    }

    @Override
    public ProtocolEntitySet build() {
        this.register0(new ProtocolEntityRegister());
        return protocolEntitySet;
    }
}
