package io.github.guoxinl.protocol.analysis.model.common;

import io.github.guoxinl.protocol.analysis.conf.convert.BooleanTypeConvert;
import io.github.guoxinl.protocol.analysis.model.anno.Protocol;
import io.github.guoxinl.protocol.analysis.model.anno.Type;
import io.github.guoxinl.protocol.analysis.model.entity.ProtocolEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 该协议对象用于接收网络请求后，响应结果成功/失败
 * <p>
 * Create by guoxin on 2018/7/30
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Protocol(commandIndex = 255, version = 1)
public class ResultProtocol implements ProtocolEntity {

    @Type(convert = BooleanTypeConvert.class)
    private Boolean code;

    /**
     * 响应成功
     *
     * @return ProtocolEntity 获得一个成功响应对象
     */
    public static ResultProtocol success() {
        return new ResultProtocol(true);
    }

    /**
     * 响应失败
     *
     * @return ProtocolEntity 获得一个失败响应对象
     */
    public static ResultProtocol failure() {
        return new ResultProtocol(false);
    }
}
