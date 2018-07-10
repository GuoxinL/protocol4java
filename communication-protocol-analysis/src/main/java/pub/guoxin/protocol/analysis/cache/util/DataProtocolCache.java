package pub.guoxin.protocol.analysis.cache.util;

import pub.guoxin.protocol.analysis.cache.ConcurrentHashMapCache;
import pub.guoxin.protocol.analysis.model.entity.DataProtocol;
import pub.guoxin.protocol.analysis.model.entity.ProtocolEntity;

/**
 * 协议对象缓存
 * <p>
 * Create by guoxin on 2018/7/10
 */
class DataProtocolCache {

    private static ConcurrentHashMapCache<Class<?>, DataProtocol<? extends ProtocolEntity>> instance;

    private DataProtocolCache() {
    }

    public static ConcurrentHashMapCache<Class<?>, DataProtocol<?>> getInstance() {
        if (instance == null) {
            synchronized (ConcurrentHashMapCache.class) {
                if (instance == null) {
                    instance = new ConcurrentHashMapCache<>();
                }
            }
        }
        return instance;
    }

}
