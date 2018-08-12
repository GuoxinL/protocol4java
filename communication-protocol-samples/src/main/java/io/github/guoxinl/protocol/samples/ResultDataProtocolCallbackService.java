package io.github.guoxinl.protocol.samples;

import io.github.guoxinl.protocol.analysis.model.DataProtocolCallbackService;
import io.github.guoxinl.protocol.analysis.model.common.ResultProtocol;

import java.util.Arrays;

/**
 * Create by guoxin on 2018/6/16
 */
public class ResultDataProtocolCallbackService implements DataProtocolCallbackService<UpgradeProtocol, ResultProtocol> {

    @Override
    public ResultProtocol call(UpgradeProtocol protocolEntity) {
        System.out.println(protocolEntity.getAaa());
        System.out.println(protocolEntity.getBbb());
        System.out.println(Arrays.toString(protocolEntity.getCcc()));
        System.out.println(Arrays.toString(protocolEntity.getDdd()));
        System.out.println(Arrays.toString(protocolEntity.getEee()));
        return ResultProtocol.success();
    }

}
