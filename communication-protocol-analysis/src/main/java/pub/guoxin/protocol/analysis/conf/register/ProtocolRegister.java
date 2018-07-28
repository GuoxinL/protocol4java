package pub.guoxin.protocol.analysis.conf.register;

/**
 * 协议对象注册接口
 * <p>
 * Created by guoxin on 18-3-8.
 */
public interface ProtocolRegister<T> {

    /**
     * 注册协议对象
     *
     * @param element
     * @return
     */
    ProtocolRegister register(T element);

}
