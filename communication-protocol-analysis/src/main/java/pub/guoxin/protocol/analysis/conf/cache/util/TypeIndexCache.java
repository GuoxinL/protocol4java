package pub.guoxin.protocol.analysis.conf.cache.util;

import pub.guoxin.protocol.analysis.conf.cache.ConcurrentHashMapCache;
import pub.guoxin.protocol.analysis.model.entity.DataProtocolIndexType;

/**
 * 类型索引缓存
 * <p>
 * Create by guoxin on 2018/7/10
 */
class TypeIndexCache {

    private static ConcurrentHashMapCache<Class<?>, DataProtocolIndexType> instance = new ConcurrentHashMapCache<>();

    private TypeIndexCache() {
    }

    /**
     * 初始化类型索引
     */
    private void init() {
//        instance.put();
    }
}
