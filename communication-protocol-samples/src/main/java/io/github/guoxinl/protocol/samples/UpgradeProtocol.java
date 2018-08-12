package io.github.guoxinl.protocol.samples;

import io.github.guoxinl.protocol.analysis.conf.convert.SignedInt2integerTypeConvert;
import io.github.guoxinl.protocol.analysis.conf.convert.SignedShort2shortTypeConvert;
import io.github.guoxinl.protocol.analysis.conf.convert.StringTypeConvert;
import io.github.guoxinl.protocol.analysis.model.anno.Callback;
import io.github.guoxinl.protocol.analysis.model.anno.Protocol;
import io.github.guoxinl.protocol.analysis.model.anno.TypeIndex;
import io.github.guoxinl.protocol.analysis.model.entity.ProtocolEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * Create by guoxin on 2018/6/13
 */
@Getter
@Setter
@Protocol(commandIndex = 1, version = 1)
@Callback(callback = ResultDataProtocolCallbackService.class)
public class UpgradeProtocol implements ProtocolEntity {

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
