package pub.guoxin.protocol.conf.adapter;

import javafx.util.Builder;
import lombok.Getter;
import lombok.Setter;
import pub.guoxin.protocol.conf.register.ProtocolEntityRegister;
import pub.guoxin.protocol.model.entity.ProtocolEntitySet;

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
