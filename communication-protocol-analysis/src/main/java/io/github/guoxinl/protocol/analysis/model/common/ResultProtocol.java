package io.github.guoxinl.protocol.analysis.model.common;

import io.github.guoxinl.protocol.analysis.conf.convert.BooleanTypeConvert;
import io.github.guoxinl.protocol.analysis.model.anno.Protocol;
import io.github.guoxinl.protocol.analysis.model.anno.TypeIndex;
import io.github.guoxinl.protocol.analysis.model.entity.ProtocolEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 该协议对象用于接收网络请求后，响应结果成功/失败
 * <p>
 * Create by guoxin on 2018/7/30
 */
@Getter
@Setter
@Protocol(commandIndex = 255, version = 1)
public class ResultProtocol implements ProtocolEntity {

    @TypeIndex(convert = BooleanTypeConvert.class)
    private Boolean code;

}
