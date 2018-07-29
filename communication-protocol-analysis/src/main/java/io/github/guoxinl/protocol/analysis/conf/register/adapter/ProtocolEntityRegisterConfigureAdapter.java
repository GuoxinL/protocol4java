package io.github.guoxinl.protocol.analysis.conf.register.adapter;

import lombok.Getter;
import lombok.Setter;
import io.github.guoxinl.protocol.analysis.conf.register.ProtocolEntityRegister;

/**
 * <p>
 * Created by guoxin on 18-3-8.
 */
@Deprecated
@Getter
@Setter
public abstract class ProtocolEntityRegisterConfigureAdapter {

    public abstract void register(ProtocolEntityRegister register);

    private void register0(ProtocolEntityRegister register) {
        register(register);
    }

}
