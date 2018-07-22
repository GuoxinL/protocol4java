package pub.guoxin.protocol.analysis;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import javafx.util.Callback;
import pub.guoxin.protocol.analysis.model.entity.DataProtocol;
import pub.guoxin.protocol.analysis.model.entity.ProtocolEntity;
import pub.guoxin.protocol.analysis.utils.ClassUtils;

import java.util.Objects;


/**
 * Created by guoxin on 18-2-25.
 */
public class DataProtocolCallback implements Callback<ByteBuf, ByteBuf> {

    @SuppressWarnings("unchecked")
    @Override
    public ByteBuf call(ByteBuf byteBuf) {
        DataProtocol dataProtocol = new DataProtocol(byteBuf);
        Class        callback     = dataProtocol.getCallback();
        Object protocolEntity = ClassUtils.methodInvoke(callback, "call", DataProtocol.class, dataProtocol);
        if (Objects.nonNull(protocolEntity)) {
            ProtocolEntity protocolEntity1 = (ProtocolEntity) protocolEntity;
            DataProtocol   result          = new DataProtocol(protocolEntity1);
            ByteBuf        buffer          = Unpooled.buffer();
            result.serialization(buffer);
            return buffer;
        }
        return null;
    }
}
