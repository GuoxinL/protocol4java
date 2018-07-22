package pub.guoxin.protocol.analysis.conf.register.adapter;

import lombok.Getter;
import lombok.Setter;
import pub.guoxin.protocol.analysis.conf.register.ProtocolEntityRegister;

/**
 * TODO 这个注册类用于之后开发spring-boot-starter-protocol4java
 * <p>
 * Created by guoxin on 18-3-8.
 */
@Deprecated
@Getter
@Setter
public abstract class ProtocolEntityRegisterConfigureAdapter {

//    private ProtocolEntitySet protocolEntitySet = ProtocolEntitySet.newProtocolEntitySet();

    public abstract void register(ProtocolEntityRegister register);

    private void register0(ProtocolEntityRegister register) {
        register(register);
//        this.protocolEntitySet.addAll(register.getRegister());
    }

}
