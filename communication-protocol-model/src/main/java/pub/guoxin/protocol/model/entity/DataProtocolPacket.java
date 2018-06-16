package pub.guoxin.protocol.model.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 数据段
 * <p>
 * Created by guoxin on 18-2-25.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataProtocolPacket implements Serializable {
    private DataProtocolCode code;
    private DataProtocolType type;
    private Object data;
}
