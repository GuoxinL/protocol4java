package pub.guoxin.protocol.model.anno;

import pub.guoxin.protocol.model.DataProtocolCallbackService;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Callback {
    /**
     * 收到协议对应的处理回调
     */
    Class<? extends DataProtocolCallbackService> callback() default DataProtocolCallbackService.class;
}
