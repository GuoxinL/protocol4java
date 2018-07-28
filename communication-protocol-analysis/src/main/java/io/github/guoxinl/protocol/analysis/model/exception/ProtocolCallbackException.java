package io.github.guoxinl.protocol.analysis.model.exception;

/**
 * 协议异常
 * <p>
 * Create by guoxin on 2018/6/14
 */
public class ProtocolCallbackException extends ProtocolException {
    public ProtocolCallbackException() {
        super();
    }

    public ProtocolCallbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProtocolCallbackException(String message) {
        super(message);
    }
}
