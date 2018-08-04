package io.github.guoxinl.protocol.samples;

import io.github.guoxinl.protocol.analysis.conf.convert.SignedInt2integerTypeConvert;
import io.github.guoxinl.protocol.analysis.conf.convert.SignedShort2shortTypeConvert;
import io.github.guoxinl.protocol.analysis.conf.convert.StringTypeConvert;
import io.github.guoxinl.protocol.analysis.model.DataProtocolCallbackService;
import io.github.guoxinl.protocol.analysis.model.anno.Callback;
import io.github.guoxinl.protocol.analysis.model.anno.CodeIndex;
import io.github.guoxinl.protocol.analysis.model.anno.Protocol;
import io.github.guoxinl.protocol.analysis.model.anno.TypeIndex;
import io.github.guoxinl.protocol.analysis.model.entity.ProtocolEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Create by guoxin on 2018/6/13
 */
@Getter
@Setter
@Protocol(commandIndex = 1, version = 1)
@Callback(callback = DataProtocolCallbackService.class)
public class TestProtocol implements ProtocolEntity {
    private List<Integer> fff;
}
