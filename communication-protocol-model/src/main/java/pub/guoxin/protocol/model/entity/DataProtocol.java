package pub.guoxin.protocol.model.entity;

import lombok.*;
import pub.guoxin.protocol.model.anno.Protocol;

import java.io.Serializable;
import java.util.List;

/**
 * Created by guoxin on 18-2-25.
 */
@Getter
@Setter
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
    private T data;

}
