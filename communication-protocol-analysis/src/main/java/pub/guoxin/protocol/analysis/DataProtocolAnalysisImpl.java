package pub.guoxin.protocol.analysis;

import lombok.Data;
import org.apache.commons.codec.DecoderException;
import org.springframework.stereotype.Component;
import pub.guoxin.protocol.analysis.utils.BytesUtils;
import pub.guoxin.protocol.analysis.utils.HexConvertUtils;
import pub.guoxin.protocol.analysis.utils.ObjectClone;
import pub.guoxin.protocol.model.anno.CodeIndex;
import pub.guoxin.protocol.model.anno.TypeIndex;
import pub.guoxin.protocol.model.entity.*;
import pub.guoxin.protocol.model.exception.ProtocolConfigException;
import pub.guoxin.protocol.model.exception.ProtocolException;
import pub.guoxin.protocol.model.exception.ProtocolNotFoundException;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.sum;
import static pub.guoxin.protocol.model.constants.DataProtocolConstants.Header;
import static pub.guoxin.protocol.model.constants.DataProtocolConstants.Packet;


/**
 * Created by guoxin on 18-2-25.
 */
@Data
@Component
public class DataProtocolAnalysisImpl implements DataProtocolAnalysis {

    @Resource(name = "dataProtocols")
    private final List<DataProtocol<ProtocolEntity>> dataProtocols;

    public DataProtocol analysisProtocolData2ProtocolEntity(String data) {
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
            return null;
        }
        DataProtocolHeader       header  = dataProtocol.getHeader();
        List<DataProtocolPacket> packets = dataProtocol.getPackets();

        int index   = header.getCommand().getIndex();
        int version = header.getVersion();

        return null;
    }

    private ProtocolEntity getData(DataProtocolHeader header, List<DataProtocolPacket> packets) {
        Class<? extends ProtocolEntity> protocolEntity = header.getProtocolEntity();
        Object                          instance       = null;
        try {
            instance = protocolEntity.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new ProtocolException("实例化协议对象失败");
        }
        for (Field declaredField : protocolEntity.getDeclaredFields()) {
            CodeIndex codeIndexAnnotation = declaredField.getAnnotation(CodeIndex.class);
            TypeIndex typeIndexAnnotation = declaredField.getAnnotation(TypeIndex.class);

            int codeIndex = codeIndexAnnotation.index();
            int typeIndex = typeIndexAnnotation.index();
            for (DataProtocolPacket packet : packets) {
                int packetCodeIndex = packet.getCode().getIndex();
                int packetTypeIndex = packet.getType().getIndex();
                if (codeIndex == packetCodeIndex && typeIndex == packetTypeIndex) {
                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(instance, packet.getData());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        // TODO 如果这个对象正在执行Java语言访问控制，并且底层子弹不可访问会出现此错误
                        throw new ProtocolNotFoundException("如果这个对象正在执行Java语言访问控制，并且底层子弹不可访问会出现此错误", e);
                    }
                }
            }

        }
        return (ProtocolEntity) instance;
    }

    private void analysisPacket(String data, List<DataProtocolPacket> dataProtocolPackets) throws DecoderException {
        int pointer = Header.TOTAL_LENGTH;
        for (DataProtocolPacket protocolPacket : dataProtocolPackets) {
            {
                String hexString = BytesUtils.subStringByIndex(data, sum(pointer, Packet.CODE_START), sum(pointer, Packet.CODE_END));
                int    code      = HexConvertUtils.hexString2Int(hexString);
                // 没事闲的在验证一遍
                if (protocolPacket.getCode().getIndex() == code) {
                    throw new ProtocolNotFoundException("二次验证 version 失败");
                }
            }
            {
                String hexString = BytesUtils.subStringByIndex(data, sum(pointer, Packet.TYPE_START), sum(pointer, Packet.TYPE_END));
                int    type      = HexConvertUtils.hexString2Int(hexString);
                // 没事闲的在验证一遍
                if (protocolPacket.getType().getIndex() == type) {
                    throw new ProtocolNotFoundException("二次验证 version 失败");
                }
            }
            {
                String hexStringLength = BytesUtils.subStringByIndex(data, sum(pointer, Packet.DATA_LENGTH_START), sum(pointer, Packet.DATA_LENGTH_END));
                int    dataLength      = HexConvertUtils.hexString2Int(hexStringLength);
                String hexString       = BytesUtils.subStringByIndex(data, sum(pointer, Packet.DATA_LENGTH_END), sum(pointer, dataLength));
                setInstanceField(protocolPacket, hexString);
                pointer += Packet.TYPE_END + dataLength;
            }

        }
        if (pointer != data.length() / 2) {
            throw new ProtocolConfigException("所接收数据与协议对象不匹配");
        }
    }

    /**
     * TODO 这里外循环是16进制字符串，内循环是对象字段，由于该方法涉及反射所以该方法循环次数将会过多，所以要减少该方法的执行次数
     *
     * @param dataProtocolPacket 数据段
     * @param hexString          16进制字符串
     * @throws DecoderException 类型解析错误
     */
    private void setInstanceField(DataProtocolPacket dataProtocolPacket, String hexString) throws DecoderException {
        DataProtocolType dataProtocolType = dataProtocolPacket.getType();
        Class<?>         clazz            = dataProtocolType.getValue();

        int      dataCodeIndex = dataProtocolPacket.getCode().getIndex();
        int      dataTypeIndex = dataProtocolPacket.getType().getIndex();
        Class<?> dataTypeValue = dataProtocolPacket.getType().getValue();

        for (Field declaredField : clazz.getDeclaredFields()) {
            CodeIndex codeIndexAnnotation = declaredField.getAnnotation(CodeIndex.class);
            TypeIndex typeIndexAnnotation = declaredField.getAnnotation(TypeIndex.class);

            int codeIndex = codeIndexAnnotation.index();
            int typeIndex = typeIndexAnnotation.index();

            if (codeIndex == dataCodeIndex && typeIndex == dataTypeIndex) {
                Object fieldValue = HexConvertUtils.getFieldValue(dataTypeValue, hexString);
                dataProtocolPacket.setData(fieldValue);
            }
        }
    }

    /**
     * @param data 16进制字符串数据
     * @return 当前协议
     * @throws DecoderException 解析失败
     */
    private DataProtocol<ProtocolEntity> currentDataProtocol(String data) throws DecoderException {
        for (DataProtocol<ProtocolEntity> dataProtocol : dataProtocols) {
            DataProtocolHeader header = dataProtocol.getHeader();
            boolean            flag;
            int                commandCode;
            int                version;
            {
                String hexString = BytesUtils.subStringByIndex(data, Header.COMMAND_START, Header.COMMAND_END);
                commandCode = HexConvertUtils.hexString2Int(hexString);
                flag = header.getCommand().getIndex() == commandCode;
            }
            {
                String hexString = BytesUtils.subStringByIndex(data, Header.VERSION_START, Header.VERSION_END);
                version = HexConvertUtils.hexString2Int(hexString);
                flag = flag && header.getVersion() == version;
            }
            if (flag) {
                // 克隆符合条件的协议
                return (DataProtocol<ProtocolEntity>) ObjectClone.deepClone(dataProtocol);
            }
        }
        throw new ProtocolNotFoundException("未找到对应协议");
    }

    private void analysisHeader(String data, DataProtocolHeader header) throws DecoderException {
        {
            String hexString   = BytesUtils.subStringByIndex(data, Header.COMMAND_START, Header.COMMAND_END);
            int    commandCode = HexConvertUtils.hexString2Int(hexString);
            // 没事闲的在验证一遍
            if (header.getCommand().getIndex() == commandCode) {
                throw new ProtocolNotFoundException("二次验证 command 失败");
            }
        }
        {
            String hexString = BytesUtils.subStringByIndex(data, Header.VERSION_START, Header.VERSION_END);
            int    version   = HexConvertUtils.hexString2Int(hexString);
            // 没事闲的在验证一遍
            if (header.getVersion() == version) {
                throw new ProtocolNotFoundException("二次验证 version 失败");
            }
        }
        {
            String hexString   = BytesUtils.subStringByIndex(data, Header.TOTAL_PACKET_START, Header.TOTAL_PACKET_END);
            int    totalPacket = HexConvertUtils.hexString2Int(hexString);
            header.setTotalPacket(totalPacket);
        }
    }

}
