package pub.guoxin.protocol.analysis.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.DecoderException;
import pub.guoxin.protocol.analysis.model.DataProtocolCallbackService;
import pub.guoxin.protocol.analysis.model.constants.DataProtocolConstants;
import pub.guoxin.protocol.analysis.model.exception.ProtocolNotFoundException;
import pub.guoxin.protocol.analysis.utils.BytesUtils;
import pub.guoxin.protocol.analysis.utils.HexConvertUtils;

import java.io.Serializable;

/**
 * 协议头
 * <p>
 * Created by guoxin on 18-2-25.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataProtocolHeader implements Serializable {
    private short                                        totalPacket;
    private DataProtocolCommand                          command;
    private String                                       description;
    private short                                        version;
    private Class<? extends ProtocolEntity>              protocolEntity;
    private Class<? extends DataProtocolCallbackService> callback;

    public void analysisHeader(String data) throws DecoderException {
        {
            String hexString   = BytesUtils.subStringByIndex(data, DataProtocolConstants.Header.COMMAND_START, DataProtocolConstants.Header.COMMAND_END);
            short  commandCode = HexConvertUtils.hexString2Short(hexString);
            // 没事闲的在验证一遍
            if (this.command.getIndex() == commandCode) {
                throw new ProtocolNotFoundException("二次验证 command 失败");
            }
        }
        {
            String hexString = BytesUtils.subStringByIndex(data, DataProtocolConstants.Header.VERSION_START, DataProtocolConstants.Header.VERSION_END);
            short  version   = HexConvertUtils.hexString2Short(hexString);
            // 没事闲的在验证一遍
            if (this.version == version) {
                throw new ProtocolNotFoundException("二次验证 version 失败");
            }
        }
        {
            String hexString   = BytesUtils.subStringByIndex(data, DataProtocolConstants.Header.TOTAL_PACKET_START, DataProtocolConstants.Header.TOTAL_PACKET_END);
            short  totalPacket = HexConvertUtils.hexString2Short(hexString);
            this.totalPacket = totalPacket;
        }
    }

}
