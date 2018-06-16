package pub.guoxin.protocol.model;

import javafx.util.Callback;
import pub.guoxin.protocol.model.anno.Protocol;
import pub.guoxin.protocol.model.entity.ProtocolEntity;

/**
 * 解析后回调接口，使用时需要实现该类，然后将实现类填写到 {@link Protocol#callback()} 中
 * <p>
 * Create by guoxin on 2018/6/14
 */
public interface DataProtocolCallbackService<P extends ProtocolEntity, R extends ProtocolEntity> extends Callback<P, R> {

    /**
     * 用于接收到请求后回调
     *
     * @param protocolEntity 请求协议对象, 协议对象需要使用{@link Protocol} 注解进行标注
     * @return 响应协议对象
     */
    R call(P protocolEntity);
}
