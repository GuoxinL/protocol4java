package io.github.protocol.analysis.model.exception;

/**
 * 协议异常
 * <p>
 * Create by guoxin on 2018/6/14
 */
public class ProtocolException extends RuntimeException {
    public ProtocolException() {
        super();
    }

    public ProtocolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProtocolException(String message) {
        super(message);
    }
}
