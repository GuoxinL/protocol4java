package pub.guoxin.protocol.analysis.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pub.guoxin.protocol.analysis.model.TypeClass;
import pub.guoxin.protocol.analysis.utils.ByteUtil;

import java.io.Serializable;
import java.lang.reflect.Field;
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
    private short     elementLength;
    /**
     * 元素数据
     */
    private Object    data;
    /**
     * 该变量不参与Element序列化
     */
    private TypeClass typeClass;

    public DataProtocolPacketElement(ByteBuffer byteBuffer, DataProtocolIndexType type, DataProtocolIndexCode code) {
        this.typeClass = TypeClass.findByIndex(type.getIndex());
        {
            // 解析长度
            short elementLength = byteBuffer.getShort();
            this.elementLength = elementLength;
        }
//        byte[] elementLengthBytes = BytesUtils.createByteArray(data,
//                Integers.sumEqualTo(p, DataProtocolConstants.Element.ELEMENT_LENGTH_START),
//                Integers.sumEqualTo(p, DataProtocolConstants.Element.ELEMENT_LENGTH_END));
//        short elementLength = ByteUtil.getShort(elementLengthBytes);
//        this.elementLength = elementLength;
        {
            // 解析数据
            byte[] bytes = ByteUtil.createEmptyByteArray(byteBuffer.position(), this.elementLength);
            byteBuffer.get(bytes);
            Object data = ByteUtil.getObject(this.typeClass, bytes);
            this.data = data;
        }
//        byte[] bytes = BytesUtils.createByteArray(data,
//                Integers.sumEqualTo(p, DataProtocolConstants.Element.ELEMENT_LENGTH_END),
//                Integers.sumEqualTo(p, (int) this.elementLength));
//        Object object = ByteUtil.getObject(this.typeClass, bytes);
//        this.data = object;
    }

    public DataProtocolPacketElement(Field field, ProtocolEntity protocolEntity) {
        this.typeClass = TypeClass.findByClass(field.getType());
        if (!Objects.isNull(protocolEntity)) {
            // 设置是否允许访问，不是修改原来的访问权限修饰词。
            field.setAccessible(true);
            try {
                // 返回输出指定对象a上此 Field表示的字段名和字段值
                this.data = field.get(protocolEntity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                // TODO 以后统一编写异常处理
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataProtocolPacketElement that = (DataProtocolPacketElement) o;
        return elementLength == that.elementLength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementLength);
    }

    @Override
    public byte[] serialization() {
        return ByteUtil.getBytes(this.typeClass, this.data);
    }
}
