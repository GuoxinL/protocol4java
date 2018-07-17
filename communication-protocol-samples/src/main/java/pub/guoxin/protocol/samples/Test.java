package pub.guoxin.protocol.samples;

import pub.guoxin.protocol.analysis.model.entity.DataProtocol;
import pub.guoxin.protocol.analysis.model.entity.ProtocolEntity;

import java.util.Arrays;

/**
 * Create by guoxin on 2018/7/8
 */
public class Test {
    public static void main(String[] args) {
        UpgradeProtocol upgradeProtocol = new UpgradeProtocol();
        upgradeProtocol.setEee(new short[] {1,2,3,4,5});
        upgradeProtocol.setCcc(new int[]{5,4,3,2,1});
        upgradeProtocol.setBbb("bbbbbbbbbbbb");
        upgradeProtocol.setDdd(new String[] {"dsa","aaa","bbb"});
        upgradeProtocol.setAaa("321312k321312");
        DataProtocol dataProtocol = new DataProtocol(upgradeProtocol);
        byte[]       serialization = dataProtocol.serialization();
        System.out.println(Arrays.toString(serialization));
        DataProtocol dataProtocol1 = new DataProtocol(serialization);
        System.out.println(dataProtocol1.toString());
        ProtocolEntity protocolEntity = dataProtocol1.protocolEntity();
        System.out.println(protocolEntity.toString());

    }
}
