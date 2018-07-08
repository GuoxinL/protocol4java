package pub.guoxin.protocol.analysis.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import pub.guoxin.protocol.analysis.model.DataProtocolCallbackService;
import pub.guoxin.protocol.analysis.model.anno.Callback;
import pub.guoxin.protocol.analysis.model.anno.Protocol;
import pub.guoxin.protocol.analysis.model.exception.ProtocolConfigException;
import pub.guoxin.protocol.analysis.utils.ArrayUtils;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * 协议对象转换处理中间适配层
 * <p>
 * Created by guoxin on 18-2-25.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataProtocol<T extends ProtocolEntity> implements Serializable, ProtocolSerialization {
    /**
     * 协议头
     */
    private DataProtocolHeader                           header;
    /**
     * 数据段
     */
    private DataProtocolPacketList                       packets;
    /**
     * 协议对象类型
     */
    private Class<? extends ProtocolEntity>              protocolEntity;
    /**
     * 协议回调
     */
    private Class<? extends DataProtocolCallbackService> callback;
    /**
     * 解析后数据
     */
    private T                                            data;

    /**
     * 初始化数据段
     *
     * @param bytes 字节流
     */
    public DataProtocol(byte[] bytes) {
        header = new DataProtocolHeader(bytes);
        packets = new DataProtocolPacketList(bytes, header.getTotalPacket());
    }

    public DataProtocol(ProtocolEntity protocolEntity){
        Class<? extends ProtocolEntity> clazz              = protocolEntity.getClass();
        Protocol                protocolAnnotation = clazz.getAnnotation(Protocol.class);
        Callback                callbackAnnotation = clazz.getAnnotation(Callback.class);
        if (Objects.isNull(protocolAnnotation)) {
            throw new ProtocolConfigException("请使用`@Protocol`对您的协议对象进行标识。");
        }
        callback = callbackAnnotation.callback();
    }
/**
浩哥你好，我正在写一个协议相关的项目，但是在实现中碰到了一个问题
 这个项目使用spring的IOC DI后来感觉这个项目不应该和spring出现强耦合，主要使用spring的地方是这个项目的配置模块，把协议对象注册到一个map中，通过IOC交予容器管理，然后在解析时使用@Autowired注入到解析流程中使用，但是我发现我脱离了Spring跟不不知道怎么实现这种情况，如果您有时间还请您指点一下
 */
    /**
     * 协议对象
     *
     * @return 字节流
     */
    @Override
    public byte[] serialization() {
        byte[] headerBytes  = header.serialization();
        byte[] packetsBytes = packets.serialization();
        byte[] bytes        = ArrayUtils.merge(headerBytes, packetsBytes);
        return bytes;
    }

}
