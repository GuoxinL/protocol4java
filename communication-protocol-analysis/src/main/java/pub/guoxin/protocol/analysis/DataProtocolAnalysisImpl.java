package pub.guoxin.protocol.analysis;

import lombok.Data;
import org.apache.commons.codec.DecoderException;
import org.springframework.stereotype.Component;
import pub.guoxin.protocol.analysis.utils.ByteUtil;
import pub.guoxin.protocol.analysis.utils.BytesUtils;
import pub.guoxin.protocol.analysis.utils.HexConvertUtils;
import pub.guoxin.protocol.analysis.utils.ObjectClone;
import pub.guoxin.protocol.analysis.model.TypeClass;
import pub.guoxin.protocol.analysis.model.anno.CodeIndex;
import pub.guoxin.protocol.analysis.model.entity.*;
import pub.guoxin.protocol.analysis.model.exception.ProtocolConfigException;
import pub.guoxin.protocol.analysis.model.exception.ProtocolException;
import pub.guoxin.protocol.analysis.model.exception.ProtocolNotFoundException;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.sum;
import static pub.guoxin.protocol.analysis.model.constants.DataProtocolConstants.Header;
import static pub.guoxin.protocol.analysis.model.constants.DataProtocolConstants.Packet;


/**
 * Created by guoxin on 18-2-25.
 */
@Data
@Component
public class DataProtocolAnalysisImpl implements DataProtocolAnalysis {

    @Resource(name = "dataProtocols")
    private final List<DataProtocol<ProtocolEntity>> dataProtocols;

    @Override
    public DataProtocol analysisProtocolData2ProtocolEntity(byte[] data) {
        DataProtocol<ProtocolEntity> currentDataProtocol;
        try {
            currentDataProtocol = currentDataProtocol(data);
            analysisHeader(data, currentDataProtocol.getHeader());
            analysisPacket(data, currentDataProtocol.getPackets());
        } catch (DecoderException e) {
            e.printStackTrace();
            throw new ProtocolConfigException("所接收数据与协议对象不匹配", e);
        }
        currentDataProtocol.setData(getData(currentDataProtocol.getHeader(), currentDataProtocol.getPackets()));
        return currentDataProtocol;
    }

    @Override
    public String analysisProtocolEntity2ProtocolData(ProtocolEntity protocolEntity) {

        DataProtocol dataProtocol = ProtocolEntity.toDataProtocol(protocolEntity);
        if (Objects.isNull(dataProtocol)) {
            throw new NullPointerException("协议对象不可以为空！");
        }
        String hexString = toHexString(dataProtocol);
        return hexString;
    }

    public String toHexString(DataProtocol dataProtocol) {
        StringBuffer hexStringBuffer = new StringBuffer();

        // 数据头
        DataProtocolHeader header = dataProtocol.getHeader();
        {
            // 协议 命令
            short  index     = header.getCommand().getIndex();
            String hexString = HexConvertUtils.short2hexString(index);
            hexStringBuffer.append(hexString);
        }
        {
            // 协议 版本
            short  version   = header.getVersion();
            String hexString = HexConvertUtils.short2hexString(version);
            hexStringBuffer.append(hexString);
        }
        {
            // 协议 数据段总包数
            short  totalPacket = header.getTotalPacket();
            String hexString   = HexConvertUtils.short2hexString(totalPacket);
            hexStringBuffer.append(hexString);
        }


        // 数据段
        List<DataProtocolPacket> packets = dataProtocol.getPackets();
        for (DataProtocolPacket packet : packets) {
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

    private ProtocolEntity getData(DataProtocolHeader header, List<DataProtocolPacket> packets) {
//        Class<? extends ProtocolEntity> protocolEntity = header.getProtocolEntity();
        Class<? extends ProtocolEntity> protocolEntity = null;
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
            for (DataProtocolPacket packet : packets) {
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

    private void analysisPacket(byte[] data, List<DataProtocolPacket> dataProtocolPackets) throws DecoderException {
        int pointer = Header.TOTAL_LENGTH;
        for (DataProtocolPacket protocolPacket : dataProtocolPackets) {
            {
                byte[] bytes = BytesUtils.createByteArray(data, sum(pointer, Packet.CODE_START), sum(pointer, Packet.CODE_END));
                short  code  = ByteUtil.getShort(bytes);
                // 没事闲的在验证一遍
                if (protocolPacket.getCode().getIndex() == code) {
                    throw new ProtocolNotFoundException("二次验证 version 失败");
                }
            }
            {
                byte[] bytes = BytesUtils.createByteArray(data, sum(pointer, Packet.TYPE_START), sum(pointer, Packet.TYPE_END));
                int    type  = ByteUtil.getShort(bytes);
                // 没事闲的在验证一遍
                if (protocolPacket.getType().getIndex() == type) {
                    throw new ProtocolNotFoundException("二次验证 version 失败");
                }
            }
            {
                byte[] lengthBytes = BytesUtils.createByteArray(data, sum(pointer, Packet.DATA_LENGTH_START), sum(pointer, Packet.DATA_LENGTH_END));
                int    dataLength      = ByteUtil.getShort(lengthBytes);
                byte[] dataBytes = BytesUtils.createByteArray(data, sum(pointer, Packet.DATA_LENGTH_END), sum(pointer, dataLength));
                setInstanceField(protocolPacket, dataBytes);
                pointer += Packet.TYPE_END + dataLength;
            }

        }
        if (pointer != data.length / 2) {
            throw new ProtocolConfigException("所接收数据与协议对象不匹配");
        }
    }

    /**
     * TODO 这里外循环是16进制字符串，内循环是对象字段，由于该方法涉及反射所以该方法循环次数将会过多，所以要减少该方法的执行次数
     *
     * @param dataProtocolPacket 数据段
     * @param bytes          16进制字符串
     * @throws DecoderException 类型解析错误
     */
    private void setInstanceField(DataProtocolPacket dataProtocolPacket, byte[] bytes) throws DecoderException {
        DataProtocolType dataProtocolType = dataProtocolPacket.getType();
        Class<?>         clazz            = dataProtocolType.getValue();

        short dataCodeIndex = dataProtocolPacket.getCode().getIndex();

        for (Field declaredField : clazz.getDeclaredFields()) {
            CodeIndex codeIndexAnnotation = declaredField.getAnnotation(CodeIndex.class);

            short codeIndex = codeIndexAnnotation.index();

            if (codeIndex == dataCodeIndex) {
                TypeClass typeClass  = TypeClass.findByClass(declaredField.getType());
                Object    fieldValue = HexConvertUtils.getFieldValueByDataType(typeClass, null /*bytes*/);
                dataProtocolPacket.setData(fieldValue);
            }
        }
    }

    /**
     * @param data 16进制字符串数据
     * @return 当前协议
     * @throws DecoderException 解析失败
     */
    private DataProtocol<ProtocolEntity> currentDataProtocol(byte[] data) throws DecoderException {
        for (DataProtocol<ProtocolEntity> dataProtocol : dataProtocols) {
            DataProtocolHeader header = dataProtocol.getHeader();
            boolean            flag;
            short              commandIndex;
            short              version;
            {
                byte[] bytes = BytesUtils.createByteArray(data, Header.COMMAND_START, Header.COMMAND_END);
                commandIndex = ByteUtil.getShort(bytes);
                flag = header.getCommand().getIndex() == commandIndex;
            }
            {
                byte[] bytes = BytesUtils.createByteArray(data, Header.VERSION_START, Header.VERSION_END);
                version = ByteUtil.getShort(bytes);
                flag = flag && header.getVersion() == version;
            }
            if (flag) {
                // 克隆符合条件的协议
                return (DataProtocol<ProtocolEntity>) ObjectClone.deepClone(dataProtocol);
            }
        }
        throw new ProtocolNotFoundException("未找到对应协议");
    }

    private void analysisHeader(byte[] data, DataProtocolHeader header) throws DecoderException {
        {
            byte[] bytes       = BytesUtils.createByteArray(data, Header.COMMAND_START, Header.COMMAND_END);
            short  commandCode = ByteUtil.getShort(bytes);
            // 没事闲的在验证一遍
            if (header.getCommand().getIndex() == commandCode) {
                throw new ProtocolNotFoundException("二次验证 command 失败");
            }
        }
        {
            byte[] bytes   = BytesUtils.createByteArray(data, Header.VERSION_START, Header.VERSION_END);
            short  version = ByteUtil.getShort(bytes);
            // 没事闲的在验证一遍
            if (header.getVersion() == version) {
                throw new ProtocolNotFoundException("二次验证 version 失败");
            }
        }
        {
            byte[] bytes       = BytesUtils.createByteArray(data, Header.TOTAL_PACKET_START, Header.TOTAL_PACKET_END);
            short  totalPacket = ByteUtil.getShort(bytes);
//            header.setTotalPacket(totalPacket);
        }
    }

}
