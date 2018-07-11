package pub.guoxin.protocol.analysis;

import javafx.util.Callback;
import pub.guoxin.protocol.analysis.model.entity.DataProtocol;
import pub.guoxin.protocol.analysis.model.entity.ProtocolEntity;
import pub.guoxin.protocol.analysis.model.exception.ProtocolCallbackException;
import pub.guoxin.protocol.analysis.model.exception.ProtocolConfigException;

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
        Method       call         = null;
        try {
            call = callback.getMethod("call", DataProtocol.class);
        } catch (NoSuchMethodException e) {
            throw new ProtocolCallbackException("未找到call方法", e);
        }
        Object instance;
        try {
            instance = callback.newInstance();
        } catch (InstantiationException e) {
//            如果Class{@link Class#newInstance}抽象类，一个接口，一个数组类，一个基本类型或void，则抛出此异常
            throw new ProtocolCallbackException("实例类型错误", e);
        } catch (IllegalAccessException e) {
            // 如果该类有无效或者不可访问构造函数
            throw new ProtocolCallbackException("该类有无效或者不可访问构造函数", e);
        }
        Object protocolEntity;
        try {
            protocolEntity = call.invoke(instance, dataProtocol);
        } catch (IllegalAccessException e) {
            // 强制执行java访问控制修饰符，则不可调用
            throw new ProtocolCallbackException("强制执行java访问控制修饰符，则不可调用", e);
        } catch (InvocationTargetException e) {
            // 如果底层方法抛出异常
            throw new ProtocolCallbackException("如果底层方法抛出异常", e);
        }
        if (Objects.nonNull(protocolEntity)) {
            ProtocolEntity protocolEntity1 = (ProtocolEntity) protocolEntity;
            DataProtocol   result          = new DataProtocol(protocolEntity1);
            return ByteBuffer.wrap(result.serialization());
        }
        return null;
    }
}
