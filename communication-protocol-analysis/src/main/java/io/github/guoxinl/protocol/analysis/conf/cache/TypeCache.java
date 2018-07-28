package io.github.guoxinl.protocol.analysis.conf.cache;

import lombok.Builder;
import lombok.Getter;
import io.github.guoxinl.protocol.analysis.conf.convert.TypeConvert;

/**
 * Create by guoxin on 2018/7/13
 */
@Builder
@Getter
public class TypeCache {
    private int                          index;
    private Class<?>                     typeClass;
    private Class<? extends TypeConvert> typeConvert;
}
