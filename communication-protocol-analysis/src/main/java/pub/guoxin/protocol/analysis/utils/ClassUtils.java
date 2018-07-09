package pub.guoxin.protocol.analysis.utils;


import pub.guoxin.protocol.analysis.conf.convert.StringTypeConvert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Create by guoxin on 2018/7/9
 */
public class ClassUtils {
    public static Class<?> getGenericsType(Class<?> clazz) {
        Type type = clazz.getGenericInterfaces()[0];
        //通过这个方法获取了一个Type对象，里面实际上包含了类的各种基本信息，如成员变量、方法、类名和泛型的信息...
        ParameterizedType pt   = (ParameterizedType) type;
        Type[]            args = pt.getActualTypeArguments();    //这是一包含了所有的泛型类型 信息的个数组
        return (Class<?>) args[0];
    }

    public static void main(String[] args) {
        Class<?> genericsType = getGenericsType(StringTypeConvert.class);
        System.out.println(genericsType);
    }
}
