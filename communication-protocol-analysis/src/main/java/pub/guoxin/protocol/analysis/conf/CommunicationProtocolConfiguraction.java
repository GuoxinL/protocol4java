package pub.guoxin.protocol.analysis.conf;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import pub.guoxin.protocol.analysis.conf.adapter.ProtocolEntityRegisterConfigureAdapter;
import pub.guoxin.protocol.analysis.model.anno.Protocol;
import pub.guoxin.protocol.analysis.model.entity.*;
import pub.guoxin.protocol.analysis.model.exception.ProtocolConfigException;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Create by guoxin on 2018/6/14
 */
@Component
@Configuration
public class CommunicationProtocolConfiguraction {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 获得所配置的协议对象
     *
     * @return 所有协议
     */
    private ProtocolEntitySet getProtocolEntitySet() {
        ProtocolEntitySet                                   classes     = ProtocolEntitySet.newProtocolEntitySet();
        Map<String, ProtocolEntityRegisterConfigureAdapter> beansOfType = applicationContext.getBeansOfType(ProtocolEntityRegisterConfigureAdapter.class);
        beansOfType.values().forEach(protocolEntityRegisterConfigureAdapter -> {
            classes.addAll(protocolEntityRegisterConfigureAdapter.build());
        });
        return classes;
    }

    /**
     * 所有协议配置
     *
     * @return 所有协议配置
     */
    @Bean("dataProtocols")
    public List<DataProtocol> getDataProtocols() {
        ProtocolEntitySet  protocolEntitySets = getProtocolEntitySet();
        List<DataProtocol> dataProtocols      = Lists.newArrayList();

        for (Class<? extends ProtocolEntity> clazz : protocolEntitySets) {
            Protocol protocolAnnotation = clazz.getAnnotation(Protocol.class);
            // 所配置对象没有标志 @Protocol 注解
            if (Objects.isNull(protocolAnnotation)) {
                throw new ProtocolConfigException("请使用 @Protocol 注解对协议对象进行标注");
            }
            DataProtocol dataProtocol = ProtocolEntity.toDataProtocol(clazz);
            dataProtocols.add(dataProtocol);
        }
        System.out.println(dataProtocols.toString());
        return dataProtocols;
    }
}
