package io.github.guoxinl.protocol.analysis.model.anno;

import io.github.guoxinl.protocol.analysis.model.DataProtocolCallbackService;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Callback {
    /**
     * 收到协议对应的处理回调
     *
     * @return skip
     */
    Class<? extends DataProtocolCallbackService> callback() default DataProtocolCallbackService.class;
}
