package io.github.guoxinl.protocol.analysis.conf.convert;

import lombok.AllArgsConstructor;

/**
 * 默认类型
 * <p>
 * Create by guoxin on 2018/8/2
 */
@AllArgsConstructor
public enum DefaultTypeClass {

    SignedByte((short) 0, Byte.class, byte.class),
    SignedShort((short) 1, Short.class, short.class),
    SignedInteger((short) 2, Integer.class, int.class),
    SignedLong((short) 3, Long.class, long.class),
    Float((short) 4, Float.class, float.class),
    Double((short) 5, Double.class, double.class),
    Boolean((short) 6, Boolean.class, boolean.class),
    String((short) 7, String.class, void.class),
    ;

    private short    index;
    private Class<?> referenceTypeClazz;
    private Class<?> primitiveTypeClass;

    /**
     * 根据类型索引寻找基本类型
     *
     * @param index 类型索引
     * @return 基本类型
     */
    public static Class<?> findByIndex(int index) {
        for (DefaultTypeClass defaultTypeClass : DefaultTypeClass.values()) {
            if (defaultTypeClass.index == index) {
                return defaultTypeClass.referenceTypeClazz;
            }
        }
        throw new IllegalArgumentException("index: " + index + "not found!");
    }

    /**
     * 根据基本类型寻找类型索引
     *
     * @param clazz 基本类型
     * @return 字段索引
     */
    public static short findTypeIndexByClass(Class<?> clazz) {
        for (DefaultTypeClass defaultTypeClass : DefaultTypeClass.values()) {
            if (defaultTypeClass.referenceTypeClazz == clazz || defaultTypeClass.primitiveTypeClass == clazz) {
                return defaultTypeClass.index;
            }
        }
        throw new IllegalArgumentException("class: " + clazz.getName() + "not found!");
    }
}
