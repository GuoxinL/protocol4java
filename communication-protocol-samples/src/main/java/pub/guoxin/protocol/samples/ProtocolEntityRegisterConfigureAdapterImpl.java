package pub.guoxin.protocol.samples;

import org.springframework.stereotype.Component;
import pub.guoxin.protocol.analysis.conf.adapter.ProtocolEntityRegisterConfigureAdapter;
import pub.guoxin.protocol.analysis.conf.register.ProtocolEntityRegister;
import pub.guoxin.protocol.analysis.model.entity.ProtocolEntitySet;

/**
 * Created by guoxin on 18-3-10.
 */
@Component
public class ProtocolEntityRegisterConfigureAdapterImpl extends ProtocolEntityRegisterConfigureAdapter {


    public static void main(String[] args) {
        ProtocolEntityRegisterConfigureAdapter abstractDataProtocolCode = new ProtocolEntityRegisterConfigureAdapterImpl();
        ProtocolEntitySet                      build                    = abstractDataProtocolCode.build();
        System.out.println(build.toString());
    }

    @Override
    public void register(ProtocolEntityRegister register) {
        register.register(UpgradeProtocol.class);
    }
}
