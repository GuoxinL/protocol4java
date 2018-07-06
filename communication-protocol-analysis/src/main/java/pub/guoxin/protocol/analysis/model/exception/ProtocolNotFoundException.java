package pub.guoxin.protocol.analysis.model.exception;

/**
 * Create by guoxin on 2018/6/14
 */

public class ProtocolNotFoundException extends RuntimeException {
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
