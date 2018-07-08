package pub.guoxin.protocol.analysis.model.entity;

import com.google.common.collect.Lists;
import pub.guoxin.protocol.analysis.model.DataProtocolCallbackService;
import pub.guoxin.protocol.analysis.model.TypeClass;
import pub.guoxin.protocol.analysis.model.anno.Callback;
import pub.guoxin.protocol.analysis.model.anno.CodeIndex;
import pub.guoxin.protocol.analysis.model.anno.Protocol;
import pub.guoxin.protocol.analysis.model.exception.ProtocolConfigException;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * 用于标记协议对象
 * <p>
 * Create by guoxin on 2018/6/16
 */
public interface ProtocolEntity {


    static DataProtocol toDataProtocol(ProtocolEntity protocolEntity) {
        Class<? extends ProtocolEntity> clazz              = protocolEntity.getClass();
        Protocol                        protocolAnnotation = clazz.getAnnotation(Protocol.class);
        Callback                        callbackAnnotation = clazz.getAnnotation(Callback.class);
        if (Objects.isNull(protocolAnnotation)) {
            return null;
        }
        DataProtocolHeader       dataProtocolHeader  = getDataProtocolHeader(protocolAnnotation, callbackAnnotation, clazz);
        List<DataProtocolPacket> dataProtocolPackets = getDataProtocolPackets(clazz, protocolEntity);
        // 反射中获取的字段顺序是不稳定的，所以按照字段顺序进行排序
        dataProtocolPackets.sort(Comparator.comparing(dataProtocolPacket -> dataProtocolPacket.getCode().getIndex()));
        DataProtocol build = DataProtocol.builder().header(dataProtocolHeader).packets((DataProtocolPacketList) dataProtocolPackets).build();
        return build;
    }

    static DataProtocol toDataProtocol(Class<? extends ProtocolEntity> clazz) {
        Protocol protocolAnnotation = clazz.getAnnotation(Protocol.class);
        Callback callbackAnnotation = clazz.getAnnotation(Callback.class);
        if (Objects.isNull(protocolAnnotation)) {
            return null;
        }
        DataProtocolHeader       dataProtocolHeader  = getDataProtocolHeader(protocolAnnotation, callbackAnnotation, clazz);
        List<DataProtocolPacket> dataProtocolPackets = getDataProtocolPackets(clazz, null);
        // 反射中获取的字段顺序是不稳定的，所以按照字段顺序进行排序
        dataProtocolPackets.sort(Comparator.comparing(dataProtocolPacket -> dataProtocolPacket.getCode().getIndex()));
        DataProtocol build = DataProtocol.builder().header(dataProtocolHeader).packets((DataProtocolPacketList) dataProtocolPackets).build();
        return build;
    }

    /**
     * 根据注解获得协议配置
     *
     * @param protocolAnnotation 协议注解
     * @param callbackAnnotation
     * @param clazz
     * @return 协议头配置
     */
    static DataProtocolHeader getDataProtocolHeader(Protocol protocolAnnotation, Callback callbackAnnotation, Class<? extends ProtocolEntity> clazz) {
        short                                        commandIndex = protocolAnnotation.commandIndex();
        short                                        version      = protocolAnnotation.version();
        String                                       description  = protocolAnnotation.description();
        Class<? extends DataProtocolCallbackService> callback     = callbackAnnotation.callback();
        return DataProtocolHeader.builder()
                .command(DataProtocolCommand.create(commandIndex, description))
//                .description(description)
                .version(version).build();
//                .callback(callback)
//                .protocolEntity(clazz).build();
    }

    /**
     * 获得数据段配置
     *
     * @param clazz          协议对象类型
     * @param protocolEntity
     * @return 所有数据段
     */
    static List<DataProtocolPacket> getDataProtocolPackets(Class<?> clazz, ProtocolEntity protocolEntity) {
        // 拼凑数据段
        List<DataProtocolPacket> dataProtocolPackets = Lists.newArrayList();
        for (Field declaredField : clazz.getDeclaredFields()) {
            CodeIndex codeIndexAnnotation = declaredField.getAnnotation(CodeIndex.class);
            if (Objects.isNull(codeIndexAnnotation)) {
                throw new ProtocolConfigException("字段" + declaredField.getName() + "请使用 @CodeIndex 注解对协议对象进行标注");
            }
            DataProtocolCode dataProtocolCode = DataProtocolCode.create(codeIndexAnnotation.index(), codeIndexAnnotation.description());

            TypeClass        byClass          = TypeClass.findByClass(declaredField.getType());
            DataProtocolType dataProtocolType = DataProtocolType.create(byClass.getIndex(), declaredField.getType());

            DataProtocolPacket build = DataProtocolPacket.builder().code(dataProtocolCode).type(dataProtocolType).build();
            dataProtocolPackets.add(build);
        }
        return dataProtocolPackets;
    }

}
