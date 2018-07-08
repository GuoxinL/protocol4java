package pub.guoxin.protocol.analysis.model.exception;

/**
 * 协议格式异常
 * <p>
 * Create by guoxin on 2018/6/14
 */
public class ProtocolFormatException extends ProtocolException {
    public ProtocolFormatException() {
        super();
    }

    public ProtocolFormatException(String message) {
        super(message);
    }

    public ProtocolFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
