package io.github.guoxinl.protocol.analysis.conf.register;

/**
 * 协议对象注册接口
 * <p>
 * Created by guoxin on 18-3-8.
 */
public interface ProtocolRegister<T> {

    /**
     * 注册协议对象
     *
     * @param element 协议对象Class
     * @return this
     */
    ProtocolRegister register(T element);

}
