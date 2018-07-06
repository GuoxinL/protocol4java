package pub.guoxin.protocol.analysis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create by guoxin on 2018/6/22
 */
@Getter
@AllArgsConstructor
public enum TypeClass {
    Byte((short) 0, Byte.class),
    Short((short) 1, Short.class),
    /**
     * 字符统一用字符串{@link TypeClass#String}表示
     */
    @Deprecated
    Char((short) 2, Character.class),
    Integer((short) 3, Integer.class),
    Long((short) 4, Long.class),
    Float((short) 5, Float.class),
    Double((short) 6, Double.class),
    Boolean((short) 7, Boolean.class),
    String((short) 8, String.class),

    ByteArray((short) 10, Byte[].class),
    ShortArray((short) 11, Short[].class),
    /**
     * 字符统一用字符串{@link TypeClass#StringArray}表示
     */
    @Deprecated
    CharArray((short) 12, Character[].class),
    IntegerArray((short) 13, Integer[].class),
    LongArray((short) 14, Long[].class),
    FloatArray((short) 15, Float[].class),
    DoubleArray((short) 16, Double[].class),
    BooleanArray((short) 17, Boolean[].class),
    StringArray((short) 18, String[].class);

    private short    index;
    private Class<?> clazz;

    public static TypeClass findByClass(Class<?> clazz){
        for (TypeClass typeClass : TypeClass.values()) {
            if (typeClass.clazz == clazz) {
                return typeClass;
            }
        }
        throw new IllegalArgumentException("不支持该类型！");
    }
    public static TypeClass findByIndex(short index){
        for (TypeClass typeClass : TypeClass.values()) {
            if (typeClass.index == index) {
                return typeClass;
            }
        }
        throw new IllegalArgumentException("不支持该类型！");
    }
}
