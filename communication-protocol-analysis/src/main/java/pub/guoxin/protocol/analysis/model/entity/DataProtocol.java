package pub.guoxin.protocol.analysis.model.entity;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.*;
import pub.guoxin.protocol.analysis.conf.cache.DataProtocolCache;
import pub.guoxin.protocol.analysis.model.DataProtocolCallbackService;
import pub.guoxin.protocol.analysis.model.anno.Callback;
import pub.guoxin.protocol.analysis.model.anno.Protocol;
import pub.guoxin.protocol.analysis.model.exception.ProtocolConfigException;
import pub.guoxin.protocol.analysis.model.exception.ProtocolException;
import pub.guoxin.protocol.analysis.model.exception.ProtocolNotFoundException;

import java.io.Serializable;
import java.util.Objects;

/**
 * 中间层: 协议适配对象
 * 协议对象转换处理中间适配层
 * <p>
 * Created by guoxin on 18-2-25.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DataProtocol implements Serializable, ProtocolSerialization {
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

    private boolean instance;

    /**
     * 解析数据
     *
     * @param bytes 字节流
     */
    public DataProtocol(byte[] bytes) {
        ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
        analysis(byteBuf);
    }

    public DataProtocol(ByteBuf byteBuffer) {
        analysis(byteBuffer);
    }

    /**
     * 协议对象转换为协议适配对象
     *
     * @param protocolEntity 协议对象
     */
    public DataProtocol(ProtocolEntity protocolEntity) {
        this(protocolEntity.getClass(), protocolEntity);
    }

    /**
     * 协议对象转换为协议适配对象
     * Warning:
     * 如果协议对象为空则认为此次操作是注册对象
     *
     * @param clazz          协议对象类型
     * @param protocolEntity 协议对象
     */
    private DataProtocol(Class<? extends ProtocolEntity> clazz, ProtocolEntity protocolEntity) {
        Protocol protocolAnnotation = clazz.getAnnotation(Protocol.class);
        Callback callbackAnnotation = clazz.getAnnotation(Callback.class);
        if (Objects.isNull(protocolAnnotation)) {
            throw new ProtocolConfigException("请使用`@Protocol`对您的协议对象进行标识。");
        }
        this.protocolEntity = clazz;
        this.callback = callbackAnnotation.callback();
        this.packets = new DataProtocolPacketList(clazz, protocolEntity);
        this.header = new DataProtocolHeader(protocolAnnotation);
        this.instance = Objects.nonNull(protocolEntity);
    }

    /**
     * 解析协议对象
     *
     * @param clazz
     * @return
     */
    public static DataProtocol analysis(Class<? extends ProtocolEntity> clazz) {
        return new DataProtocol(clazz, null);
    }

    public void analysis(ByteBuf byteBuf) {
        this.header = new DataProtocolHeader(byteBuf);
        DataProtocol dataProtocol = DataProtocolCache.getInstance().get(header.getProtocolKey());
        if (Objects.isNull(dataProtocol)) {
            throw new ProtocolNotFoundException("该协议未找到！");
        }
        this.protocolEntity = dataProtocol.getProtocolEntity();
        this.callback = dataProtocol.getCallback();
        this.packets = new DataProtocolPacketList(byteBuf);
    }

    public ProtocolEntity protocolEntity() {
        Object instance;
        try {
            instance = this.protocolEntity.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new ProtocolException("实例化协议对象失败");
        }
        packets.protocolEntity(instance, this.protocolEntity.getDeclaredFields());
        return (ProtocolEntity) instance;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataProtocol that = (DataProtocol) o;
        return Objects.equals(this.header, that.header) && Objects.equals(this.packets, that.packets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.header, this.packets);
    }

    /**
     * 协议对象
     *
     * @param byteBuf 字节流
     */
    @Override
    public void serialization(ByteBuf byteBuf) {
        if (!this.instance) {
            throw new ProtocolException("该协议适配对象，不是实例不可以序列化");
        }
        this.header.serialization(byteBuf);
        this.packets.serialization(byteBuf);
    }

}
