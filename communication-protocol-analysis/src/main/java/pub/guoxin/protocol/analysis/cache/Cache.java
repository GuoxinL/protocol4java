package pub.guoxin.protocol.analysis.cache;

import java.util.Map;

/**
 * Create by guoxin on 2018/7/9
 */
public interface Cache<K, V> {

    V get(K key);

    Map<K, V> getAllPresent(Iterable<K> keys);

    void put(K key, V value);

    void putAll(Map<? extends K, ? extends V> m);

    void invalidate(Object key);

    void invalidateAll(Iterable<?> keys);

    void invalidateAll();

    long size();

}
