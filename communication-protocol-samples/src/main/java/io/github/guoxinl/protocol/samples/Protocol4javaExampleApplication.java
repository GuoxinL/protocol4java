package io.github.guoxinl.protocol.samples;

import io.github.guoxinl.protocol.analysis.DataProtocolCallback;
import io.github.guoxinl.protocol.analysis.conf.convert.SignedInt2integerTypeConvert;
import io.github.guoxinl.protocol.analysis.conf.convert.SignedShort2shortTypeConvert;
import io.github.guoxinl.protocol.analysis.conf.convert.StringTypeConvert;
import io.github.guoxinl.protocol.analysis.conf.register.ProtocolEntityRegister;
import io.github.guoxinl.protocol.analysis.model.DataProtocolCallbackService;
import io.github.guoxinl.protocol.analysis.model.anno.Callback;
import io.github.guoxinl.protocol.analysis.model.anno.Protocol;
import io.github.guoxinl.protocol.analysis.model.anno.TypeIndex;
import io.github.guoxinl.protocol.analysis.model.common.ResultProtocol;
import io.github.guoxinl.protocol.analysis.model.entity.DataProtocol;
import io.github.guoxinl.protocol.analysis.model.entity.ProtocolEntity;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Create by guoxin on 2018/6/14
 */

public class Protocol4javaExampleApplication {

    /**
     * 协议请求对象
     */
    @Getter
    @Setter
    @Protocol(commandIndex = 1, version = 1)
    @Callback(callback = ResultDataProtocolCallbackService.class)
    public static class UpgradeProtocol implements ProtocolEntity {

        @TypeIndex(convert = StringTypeConvert.class)
        private String aaa;

        @TypeIndex(convert = StringTypeConvert.class)
        private String bbb;

        @TypeIndex(convert = SignedInt2integerTypeConvert.class)
        private int[] ccc;

        @TypeIndex(convert = StringTypeConvert.class)
        private String[] ddd;

        @TypeIndex(convert = SignedShort2shortTypeConvert.class)
        private short[] eee;
    }

    /**
     * 回调业务逻辑类
     */
    @Slf4j
    public static class ResultDataProtocolCallbackService implements DataProtocolCallbackService<UpgradeProtocol, ResultProtocol> {

        @Override
        public ResultProtocol call(UpgradeProtocol protocolEntity) {
            log.info("Aaa: {}", protocolEntity.getAaa());
            log.info("Bbb: {}", protocolEntity.getBbb());
            log.info("Ccc: {}", Arrays.toString(protocolEntity.getCcc()));
            log.info("Ddd: {}", Arrays.toString(protocolEntity.getDdd()));
            log.info("Eee: {}", Arrays.toString(protocolEntity.getEee()));

            // 默认协议响应对象
            return ResultProtocol.success();
        }

    }

    public static void main(String[] args) throws IllegalAccessException {

        // 注册协议对象
        ProtocolEntityRegister register = new ProtocolEntityRegister();
        register.register(UpgradeProtocol.class);

        // 实例化协议对象并赋值
        UpgradeProtocol upgradeProtocol = new UpgradeProtocol();
        upgradeProtocol.setEee(new short[] {1,2,3,4,5});
        upgradeProtocol.setCcc(new int[]{5,4,3,2,1});
        upgradeProtocol.setBbb("bbb");
        upgradeProtocol.setDdd(new String[] {"ddd","ddd","ddd"});
        upgradeProtocol.setAaa("aaa");

        // 将协议对象转换为协议适配对象
        DataProtocol dataProtocol1 = DataProtocol.convert(upgradeProtocol);

        // 将协议适配对象转化为字节流
        ByteBuf      buffer        = Unpooled.buffer();
        dataProtocol1.serialization(buffer);
        System.out.println("request：" + ByteBufUtil.hexDump(buffer));

        // 模拟业务逻辑
        DataProtocolCallback callback = new DataProtocolCallback();
        ByteBuf              call     = callback.call(buffer);
        System.out.println("response：" + ByteBufUtil.hexDump(call));

    }
}
