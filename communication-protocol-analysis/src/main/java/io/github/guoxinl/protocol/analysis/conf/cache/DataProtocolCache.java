package io.github.guoxinl.protocol.analysis.conf.cache;

import io.github.guoxinl.protocol.analysis.model.entity.DataProtocol;

/**
 * 协议对象缓存
 * <p>
 * Create by guoxin on 2018/7/10
 */
public class DataProtocolCache {

    private static ConcurrentHashMapCache<String, DataProtocol> instance = new ConcurrentHashMapCache<>();

    private DataProtocolCache() {
    }

    public static ConcurrentHashMapCache<String, DataProtocol> getInstance() {
        return instance;
    }

}
