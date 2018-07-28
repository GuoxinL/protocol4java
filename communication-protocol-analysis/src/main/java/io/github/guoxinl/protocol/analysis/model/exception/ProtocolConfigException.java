package io.github.guoxinl.protocol.analysis.model.exception;

/**
 * 协议配置错误
 * <p>
 * Create by guoxin on 2018/6/14
 */
public class ProtocolConfigException extends ProtocolException {
    public ProtocolConfigException() {
        super();
    }

    public ProtocolConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProtocolConfigException(String message) {
        super(message);
    }
}
