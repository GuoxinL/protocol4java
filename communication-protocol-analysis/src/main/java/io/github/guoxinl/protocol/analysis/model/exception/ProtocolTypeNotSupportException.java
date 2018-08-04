package io.github.guoxinl.protocol.analysis.model.exception;

/**
 * 不支持协议类型
 * <p>
 * Create by guoxin on 2018/6/14
 */
public class ProtocolTypeNotSupportException extends ProtocolException {
    public ProtocolTypeNotSupportException() {
        super();
    }

    public ProtocolTypeNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProtocolTypeNotSupportException(String message) {
        super(message);
    }
}
