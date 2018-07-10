package pub.guoxin.protocol.analysis.cache.util;

import pub.guoxin.protocol.analysis.cache.ConcurrentHashMapCache;
import pub.guoxin.protocol.analysis.model.entity.DataProtocolIndexType;

/**
 * 类型索引缓存
 * <p>
 * Create by guoxin on 2018/7/10
 */
class TypeIndexCache {

    private static ConcurrentHashMapCache<Class<?>, DataProtocolIndexType> instance;

    private TypeIndexCache() {
    }

    public static ConcurrentHashMapCache<Class<?>, DataProtocolIndexType> getInstance() {
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
