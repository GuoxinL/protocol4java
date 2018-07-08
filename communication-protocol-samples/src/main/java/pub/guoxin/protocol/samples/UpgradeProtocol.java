package pub.guoxin.protocol.samples;

import lombok.Getter;
import lombok.Setter;
import pub.guoxin.protocol.analysis.model.DataProtocolCallbackService;
import pub.guoxin.protocol.analysis.model.anno.Callback;
import pub.guoxin.protocol.analysis.model.anno.CodeIndex;
import pub.guoxin.protocol.analysis.model.anno.Protocol;
import pub.guoxin.protocol.analysis.model.entity.ProtocolEntity;

/**
 * Create by guoxin on 2018/6/13
 */
@Getter
@Setter
@Protocol(commandIndex = 1, version = 1)
@Callback(callback = DataProtocolCallbackService.class)
public class UpgradeProtocol implements ProtocolEntity {
    @CodeIndex(index = 1, description = "aaaaa")
    private String aaa;
    @CodeIndex(index = 2, description = "bbbbb")
    private String bbb;
}
