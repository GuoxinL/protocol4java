package io.github.guoxinl.protocol.samples;

import io.github.guoxinl.protocol.analysis.DataProtocolCallback;
import io.github.guoxinl.protocol.analysis.conf.register.ProtocolEntityRegister;
import io.github.guoxinl.protocol.analysis.model.entity.DataProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

import java.util.Arrays;

/**
 * Create by guoxin on 2018/6/14
 */
public class Application {

    public static void main(String[] args) throws IllegalAccessException {


        ProtocolEntityRegister register = new ProtocolEntityRegister();
        register.register(UpgradeProtocol.class);


        UpgradeProtocol upgradeProtocol = new UpgradeProtocol();
        upgradeProtocol.setEee(new short[] {1,2,3,4,5});
        upgradeProtocol.setCcc(new int[]{5,4,3,2,1});
        upgradeProtocol.setBbb("bbbbbbbbbbbb");
        upgradeProtocol.setDdd(new String[] {"dsa","aaa","bbb"});
        upgradeProtocol.setAaa("aaaaaaaaa");

        DataProtocol dataProtocol1 = DataProtocol.convert(upgradeProtocol);
        System.out.println(dataProtocol1.toString());
        ByteBuf      buffer        = Unpooled.buffer();
        dataProtocol1.serialization(buffer);
        System.out.println("request：" + ByteBufUtil.hexDump(buffer));

        DataProtocolCallback callback = new DataProtocolCallback();
        ByteBuf              call     = callback.call(buffer);
        System.out.println("response：" + ByteBufUtil.hexDump(call));

    }
}
