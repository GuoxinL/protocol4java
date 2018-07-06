package pub.guoxin.protocol.analysis.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pub.guoxin.protocol.analysis.model.TypeClass;
import pub.guoxin.protocol.analysis.model.anno.CodeIndex;
import pub.guoxin.protocol.analysis.model.exception.ProtocolException;
import pub.guoxin.protocol.analysis.utils.HexConvertUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by guoxin on 18-2-25.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataProtocol<T extends ProtocolEntity> implements Serializable {
    /**
     * 协议头
     */
    private DataProtocolHeader       header;
    /**
     * 数据段
     */
    private List<DataProtocolPacket> packets;
    /**
     * 解析后数据
     */
    private T                        data;

    private ProtocolEntity getProtocolEntity() {
        Class<? extends ProtocolEntity> protocolEntity = this.header.getProtocolEntity();
        Object                          instance       = null;
        try {
            instance = protocolEntity.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new ProtocolException("实例化协议对象失败");
        }
        for (Field declaredField : protocolEntity.getDeclaredFields()) {
            CodeIndex codeIndexAnnotation = declaredField.getAnnotation(CodeIndex.class);

            short codeIndex = codeIndexAnnotation.index();
            for (DataProtocolPacket packet : this.packets) {
                short packetCodeIndex = packet.getCode().getIndex();
                if (codeIndex == packetCodeIndex) {
                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(instance, packet.getData());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        // TODO 如果这个对象正在执行Java语言访问控制，并且底层子弹不可访问会出现此错误
                        throw new ProtocolException("如果这个对象正在执行Java语言访问控制 ，并且底层子弹不可访问会出现此错误", e);
                    }
                }
            }

        }
        return (ProtocolEntity) instance;
    }

    public String toHexString(DataProtocol dataProtocol) {
        StringBuffer hexStringBuffer = new StringBuffer();

        // 数据头
        {
            // 协议 命令
            short  index     = this.header.getCommand().getIndex();
            String hexString = HexConvertUtils.short2hexString(index);
            hexStringBuffer.append(hexString);
        }
        {
            // 协议 版本
            short  version   = this.header.getVersion();
            String hexString = HexConvertUtils.short2hexString(version);
            hexStringBuffer.append(hexString);
        }
        {
            // 协议 数据段总包数
            short  totalPacket = this.header.getTotalPacket();
            String hexString   = HexConvertUtils.short2hexString(totalPacket);
            hexStringBuffer.append(hexString);
        }


        // 数据段
        for (DataProtocolPacket packet : this.packets) {
            {
                // 拼凑字段索引
                short  codeIndex = packet.getCode().getIndex();
                String hexString = HexConvertUtils.short2hexString(codeIndex);
                hexStringBuffer.append(hexString);
            }
            {
                // 拼凑类型索引
                short  typeIndex = packet.getType().getIndex();
                String hexString = HexConvertUtils.short2hexString(typeIndex);
                hexStringBuffer.append(hexString);
            }
            {
                // 拼凑长度，拼凑数据
                Class<?>  value                = packet.getType().getValue();
                Object    data                 = packet.getData();
                TypeClass byClass              = TypeClass.findByClass(value);
                String    dataHexString        = HexConvertUtils.getHexStringByDataType(byClass, data);
                String    totalLengthHexString = HexConvertUtils.short2hexString((short) (dataHexString.length() / 2));
                hexStringBuffer.append(totalLengthHexString);
                hexStringBuffer.append(dataHexString);

            }
        }
        String string = hexStringBuffer.toString();
        return string;
    }

}
