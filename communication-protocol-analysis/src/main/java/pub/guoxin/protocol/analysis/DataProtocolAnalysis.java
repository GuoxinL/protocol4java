package pub.guoxin.protocol.analysis;

import pub.guoxin.protocol.analysis.model.entity.DataProtocol;
import pub.guoxin.protocol.analysis.model.entity.ProtocolEntity;

public interface DataProtocolAnalysis {
    DataProtocol analysisProtocolData2ProtocolEntity(byte[] dataProtocol);

    String analysisProtocolEntity2ProtocolData(ProtocolEntity protocolEntity);

}
