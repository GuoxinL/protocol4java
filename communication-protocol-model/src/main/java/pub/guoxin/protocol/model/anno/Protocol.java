package pub.guoxin.protocol.model.anno;

import pub.guoxin.protocol.model.DataProtocolCallbackService;

import java.lang.annotation.*;

/**
 * 用于将一个对象标注为协议对象
 * <p>
 * 警告：
 * 1. 请给协议对象添加有效的构造函数
 * 2. 请给协议对象添加空构造函数
 * <p>
 * Create by guoxin on 2018/6/13
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Protocol {

    /**
     * 协议头：命令索引
     */
    short commandIndex();

    /**
     * 协议版本
     */
    short version();

    /**
     * 描述
     */
    String description() default "";

    /**
     * 收到协议对应的处理回调
     */
    Class<? extends DataProtocolCallbackService> callback() default DataProtocolCallbackService.class;
}