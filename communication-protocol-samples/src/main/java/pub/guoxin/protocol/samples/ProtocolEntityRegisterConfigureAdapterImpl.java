package pub.guoxin.protocol.samples;

import org.springframework.stereotype.Component;
import pub.guoxin.protocol.analysis.conf.entity.ProtocolEntitySet;
import pub.guoxin.protocol.analysis.conf.register.adapter.ProtocolEntityRegisterConfigureAdapter;
import pub.guoxin.protocol.analysis.conf.register.ProtocolEntityRegister;

/**
 * Created by guoxin on 18-3-10.
 */
public class ProtocolEntityRegisterConfigureAdapterImpl extends ProtocolEntityRegisterConfigureAdapter {

    public static void main(String[] args) {
//        ProtocolEntityRegisterConfigureAdapter abstractDataProtocolCode = new ProtocolEntityRegisterConfigureAdapterImpl();
//        ProtocolEntitySet                      build                    = abstractDataProtocolCode.build();
//        System.out.println(build.toString());
    }

    @Override
    public void register(ProtocolEntityRegister register) {
        register.register(UpgradeProtocol.class);
    }
}
