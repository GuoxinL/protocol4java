package pub.guoxin.protocol.analysis.model.entity;

import lombok.*;
import pub.guoxin.protocol.analysis.conf.cache.TypeCache;
import pub.guoxin.protocol.analysis.conf.cache.TypeIndexCache;
import pub.guoxin.protocol.analysis.conf.convert.TypeConvert;
import pub.guoxin.protocol.analysis.model.anno.CodeIndex;
import pub.guoxin.protocol.analysis.model.anno.TypeIndex;
import pub.guoxin.protocol.analysis.model.exception.ProtocolException;
import pub.guoxin.protocol.analysis.utils.ArrayUtils;
import pub.guoxin.protocol.analysis.utils.ByteUtil;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * 协议：数据段
 * <p>
 * Created by guoxin on 18-2-25.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DataProtocolPacket implements Serializable, ProtocolSerialization {
    /**
     * 字段
     */
    private DataProtocolIndexCode         code;
    /**
     * 类型
     */
    private DataProtocolIndexType         type;
    /**
     * 元素数量
     */
    private Short                         elementSize;
    /**
     * 元素集合
     */
    private DataProtocolPacketElementList elements;

    /**
     * 解析数据
     *
     * @param byteBuffer 字节流
     */
    public DataProtocolPacket(ByteBuffer byteBuffer) {
        {
            short codeIndex = byteBuffer.getShort();
            this.code = DataProtocolIndexCode.create(codeIndex);
        }
        {
            short                        codeIndex   = byteBuffer.getShort();
            TypeCache                    typeCache   = TypeIndexCache.getInstance().get(codeIndex);
            Class<? extends TypeConvert> typeConvert = typeCache.getTypeConvert();
            this.type = DataProtocolIndexType.create(codeIndex, typeConvert); // TODO
        }
        {
            short elementSize = byteBuffer.getShort();
            this.elementSize = elementSize;
        }
        {
            this.elements = new DataProtocolPacketElementList(byteBuffer, this.type.getType(), this.code, this.elementSize);
        }
    }


    public DataProtocolPacket(Field declaredField, CodeIndex codeIndexAnnotation, TypeIndex typeIndexAnnotation, ProtocolEntity protocolEntity) {
        this.code = DataProtocolIndexCode.create(codeIndexAnnotation.index(), codeIndexAnnotation.description());
        boolean                      isArray     = declaredField.getType().isArray();
        short                        typeIndex   = TypeConvert.getTypeIndex(typeIndexAnnotation.convert());
        TypeCache                    typeCache   = TypeIndexCache.getInstance().get(typeIndex);
        Class<? extends TypeConvert> typeConvert = typeCache.getTypeConvert();

        this.type = DataProtocolIndexType.create(typeIndex, typeConvert);
        if (Objects.nonNull(protocolEntity)) {
            this.elements = new DataProtocolPacketElementList(declaredField, protocolEntity, typeConvert, isArray);
            this.elementSize = (short) elements.size();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataProtocolPacket that = (DataProtocolPacket) o;
        return Objects.equals(code, that.code) && Objects.equals(type, that.type) && Objects.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, type, elements);
    }

    @Override
    public byte[] serialization() {
        byte[] result;
        {
            result = ByteUtil.getBytes(this.code.getIndex());
        }
        {
            byte[] bytes = ByteUtil.getBytes(this.type.getIndex());
            result = ArrayUtils.merge(result, bytes);
        }
        {
            byte[] bytes = ByteUtil.getBytes(this.elementSize);
            result = ArrayUtils.merge(result, bytes);
        }
        {
            byte[] bytes = elements.serialization();
            result = ArrayUtils.merge(result, bytes);
        }
        return result;
    }

    public void protocolEntity(Object instance, short codeIndex, Field declaredField) {
        if (codeIndex == this.getCode().getIndex()) {
            declaredField.setAccessible(true);
            try {
                declaredField.set(instance, /*packet.getData()*/null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                // TODO 如果这个对象正在执行Java语言访问控制，并且底层子弹不可访问会出现此错误
                throw new ProtocolException("如果这个对象正在执行Java语言访问控制 ，并且底层子弹不可访问会出现此错误", e);
            }
        }
    }
}
