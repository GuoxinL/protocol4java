package pub.guoxin.protocol.analysis.conf.register;

/**
 * Created by guoxin on 18-3-8.
 */
public interface ProtocolRegister<T> {

    ProtocolRegister register(T element);

}
