package io.github.guoxinl.protocol.analysis.model;

import io.github.guoxinl.protocol.analysis.Callback;
import io.github.guoxinl.protocol.analysis.model.anno.Protocol;
import io.github.guoxinl.protocol.analysis.model.entity.ProtocolEntity;

/**
 * 解析后回调接口，使用时需要实现该类，然后将实现类填写到 {@link io.github.guoxinl.protocol.analysis.model.anno.Callback#callback()} 中
 * <p>
 * Create by guoxin on 2018/6/14
 */
public interface DataProtocolCallbackService<Request extends ProtocolEntity, Response extends ProtocolEntity> extends Callback<Request, Response> {

    /**
     * 用于接收到请求后回调
     *
     * @param protocolEntity 请求协议对象, 协议对象需要使用{@link Protocol} 注解进行标注
     * @return 响应协议对象
     */
    Response call(Request protocolEntity);
}
