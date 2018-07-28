package io.github.protocol.analysis.model.exception;

/**
 * 协议未找到
 * <p>
 * Create by guoxin on 2018/6/14
 */
public class TypeCacheNotFoundException extends ProtocolException {
    public TypeCacheNotFoundException() {
        super();
    }

    public TypeCacheNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeCacheNotFoundException(String message) {
        super(message);
    }
}
