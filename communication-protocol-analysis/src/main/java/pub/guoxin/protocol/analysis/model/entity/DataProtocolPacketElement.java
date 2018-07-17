package pub.guoxin.protocol.analysis.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pub.guoxin.protocol.analysis.conf.convert.TypeConvert;
import pub.guoxin.protocol.analysis.utils.ByteUtil;
import pub.guoxin.protocol.analysis.utils.ClassUtils;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * 协议：数据段 - 元素
 * Create by guoxin on 2018/7/8
 */
@NoArgsConstructor
@Getter
public class DataProtocolPacketElement implements Serializable, ProtocolSerialization {

    /**
     * 元素长度
     */
    private short                        elementLength;
    /**
     * 元素数据
     */
    private Object                       data;
    /**
     * 该变量不参与Element序列化
     */
    private Class<? extends TypeConvert> typeClass;


    public DataProtocolPacketElement(Object object, Class<? extends TypeConvert> typeConvert) {
        this.data = object;
        this.typeClass = typeConvert;
    }

    public DataProtocolPacketElement(ByteBuffer byteBuffer, Class<? extends TypeConvert> type, DataProtocolIndexCode code) {
        this.typeClass = type;
        {
            // 解析长度
            short elementLength = byteBuffer.getShort();
            this.elementLength = elementLength;
        }
        {
            // 解析数据
            byte[] bytes = ByteUtil.createEmptyByteArray(byteBuffer.position(), this.elementLength);
            byteBuffer.get(bytes);
            // T decode(byte[] bytes);
            Object data = ClassUtils.methodInvoke(this.typeClass, "decode", byte[].class, bytes);
            this.data = data;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataProtocolPacketElement that = (DataProtocolPacketElement) o;
        return this.elementLength == that.elementLength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementLength);
    }

    @Override
    public byte[] serialization() {
        // byte[] encode(T t);
        Object data  = ClassUtils.methodInvoke(this.typeClass, "encode", Object.class, this.data);
        byte[] bytes = (byte[]) data;
        this.elementLength = (short) bytes.length;
        return bytes;
    }


}
