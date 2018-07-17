package pub.guoxin.protocol.analysis;

import javafx.util.Callback;
import pub.guoxin.protocol.analysis.model.entity.DataProtocol;
import pub.guoxin.protocol.analysis.model.entity.ProtocolEntity;
import pub.guoxin.protocol.analysis.model.exception.ProtocolCallbackException;
import pub.guoxin.protocol.analysis.utils.ClassUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Objects;


/**
 * Created by guoxin on 18-2-25.
 */
public class DataProtocolCallback implements Callback<ByteBuffer, ByteBuffer> {

    @SuppressWarnings("unchecked")
    @Override
    public ByteBuffer call(ByteBuffer byteBuffer) {
        DataProtocol dataProtocol = new DataProtocol(byteBuffer);
        Class        callback     = dataProtocol.getCallback();
        Object protocolEntity = ClassUtils.methodInvoke(callback, "call", DataProtocol.class, dataProtocol);
        if (Objects.nonNull(protocolEntity)) {
            ProtocolEntity protocolEntity1 = (ProtocolEntity) protocolEntity;
            DataProtocol   result          = new DataProtocol(protocolEntity1);
            return ByteBuffer.wrap(result.serialization());
        }
        return null;
    }
}
