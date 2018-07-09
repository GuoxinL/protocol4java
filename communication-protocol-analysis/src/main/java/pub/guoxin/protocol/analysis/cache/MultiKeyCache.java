package pub.guoxin.protocol.analysis.cache;

import pub.guoxin.protocol.analysis.model.entity.DataProtocol;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create by guoxin on 2018/7/9
 */
public abstract class MultiKeyCache {

    private static ConcurrentHashMap<List<Integer>, DataProtocol> concurrentHashMap = new ConcurrentHashMap<>();

    public DataProtocol get(Integer key) {
        for (Map.Entry<List<Integer>, DataProtocol> listDataProtocolEntry : concurrentHashMap.entrySet()) {
            listDataProtocolEntry.getKey().contains(key);
        }

        return null;
    }

    public Map<Integer, DataProtocol> getAllPresent(Iterable<Integer> keys) {
        return null;
    }

    public void put(Integer key, DataProtocol value) {

    }

    public void putAll(Map<? extends Integer, ? extends DataProtocol> m) {

    }

    public void invalidate(Object key) {

    }

    public void invalidateAll(Iterable<?> keys) {

    }

    public void invalidateAll() {

    }

    public long size() {
        return 0;
    }
}
