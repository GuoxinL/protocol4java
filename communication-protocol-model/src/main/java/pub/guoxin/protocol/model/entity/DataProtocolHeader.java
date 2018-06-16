package pub.guoxin.protocol.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pub.guoxin.protocol.model.DataProtocolCallbackService;

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
    private int                                          totalPacket;
    private DataProtocolCommand                          command;
    private String                                       description;
    private int                                          version;
    private Class<? extends ProtocolEntity>              protocolEntity;
    private Class<? extends DataProtocolCallbackService> callback;
}
