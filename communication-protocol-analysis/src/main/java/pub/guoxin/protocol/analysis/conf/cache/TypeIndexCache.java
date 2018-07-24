package pub.guoxin.protocol.analysis.conf.cache;

import pub.guoxin.protocol.analysis.conf.convert.*;

/**
 * 类型索引缓存
 * <p>
 * Create by guoxin on 2018/7/10
 */
public class TypeIndexCache {

    private static ConcurrentHashMapCache<Integer, TypeCache> instance = new ConcurrentHashMapCache<>();

    static {
        init();
    }

    private TypeIndexCache() {
    }

    public static ConcurrentHashMapCache<Integer, TypeCache> getInstance() {
        return instance;
    }

    /**
     * 初始化类型索引
     */
    private static void init() {
        loadTypeConvert(SignedChar2byteTypeConvert.class);
        loadTypeConvert(SignedShort2shortTypeConvert.class);
        loadTypeConvert(SignedInt2integerTypeConvert.class);
        loadTypeConvert(SignedLongLong2longTypeConvert.class);
        loadTypeConvert(FloatTypeConvert.class);
        loadTypeConvert(DoubleTypeConvert.class);
        loadTypeConvert(BooleanTypeConvert.class);
        loadTypeConvert(StringTypeConvert.class);

        loadTypeConvert(UnsignedChar2byteTypeConvert.class);
        loadTypeConvert(UnsignedShort2shortTypeConvert.class);
        loadTypeConvert(UnsignedInt2integerTypeConvert.class);
        loadTypeConvert(UnsignedLongLong2longTypeConvert.class);
    }

    private static void loadTypeConvert(Class<? extends TypeConvert> clazz) {
        int       typeIndex = TypeConvert.getTypeIndex(clazz);
        TypeCache typeCache = TypeConvert.getTypeCache(clazz, typeIndex);
        instance.put(typeIndex, typeCache);
    }

}
