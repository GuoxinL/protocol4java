package io.github.protocol.analysis.conf.cache;

import java.util.Map;

/**
 * Create by guoxin on 2018/7/9
 */
public interface Cache<K, V> {

    V get(K key);

    Map<K, V> getAllPresent(Iterable<K> keys) throws Exception;

    void put(K key, V value);

    void putAll(Map<? extends K, ? extends V> m);

    void invalidate(K key);

    void invalidateAll(Iterable<K> keys) throws Exception;

    void invalidateAll();

    long size();

    boolean exists(K key);

}
