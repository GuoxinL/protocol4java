package io.github.protocol.analysis.conf.cache;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO 异常处理统一写
 * <p>
 * Create by guoxin on 2018/7/10
 */
public class ConcurrentHashMapCache<K, V> implements Cache<K, V> {

    private Map<K, V> concurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public V get(K key) {
        return concurrentHashMap.get(key);
    }

    @Override
    public Map<K, V> getAllPresent(Iterable<K> keys) throws Exception {
        throw new Exception();
    }

    @Override
    public void put(K key, V value) {
        concurrentHashMap.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        concurrentHashMap.putAll(m);
    }

    @Override
    public void invalidate(K key) {
        concurrentHashMap.remove(key);
    }

    @Override
    public void invalidateAll(Iterable<K> keys) throws Exception {
        throw new Exception();
    }

    @Override
    public void invalidateAll() {
        concurrentHashMap.clear();
    }

    @Override
    public long size() {
        return concurrentHashMap.size();
    }

    @Override
    public boolean exists(K key) {
        if (concurrentHashMap.isEmpty()) {
            return false;
        }
        if (Objects.isNull(concurrentHashMap.get(key))) {
            return false;
        }
        return true;
    }

    public Set<K> keys(){
        return concurrentHashMap.keySet();
    }
}
