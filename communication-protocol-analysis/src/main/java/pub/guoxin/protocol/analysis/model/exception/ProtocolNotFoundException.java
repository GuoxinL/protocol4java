package pub.guoxin.protocol.analysis.model.exception;

/**
 * 协议未找到
 * <p>
 * Create by guoxin on 2018/6/14
 */
public class ProtocolNotFoundException extends ProtocolException {
    public ProtocolNotFoundException() {
        super();
    }

    public ProtocolNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProtocolNotFoundException(String message) {
        super(message);
    }
}
