package pub.guoxin.protocol.analysis;

import javafx.util.Callback;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pub.guoxin.protocol.model.DataProtocolCallbackService;
import pub.guoxin.protocol.model.entity.DataProtocol;
import pub.guoxin.protocol.model.entity.ProtocolEntity;


/**
 * Created by guoxin on 18-2-25.
 */
@Data
@Component
public class DataProtocolCallback implements Callback<String, String> {

    @Autowired
    private DataProtocolAnalysis dataProtocolAnalysis;
    @Autowired
    private ApplicationContext   applicationContext;

    @Override
    public String call(String protocolData) {
        // 解析16进制字符串
        DataProtocol dataProtocol = dataProtocolAnalysis.analysisProtocolData2ProtocolEntity(protocolData);
        // 回调用户逻辑
        DataProtocolCallbackService bean   = applicationContext.getBean(dataProtocol.getHeader().getCallback());
        ProtocolEntity              result = bean.call(dataProtocol.getData());
        // 返回值转换
        String s = dataProtocolAnalysis.analysisProtocolEntity2ProtocolData(result);
        return s;
    }
}
