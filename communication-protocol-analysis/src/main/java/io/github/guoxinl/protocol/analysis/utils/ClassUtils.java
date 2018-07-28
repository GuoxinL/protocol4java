package io.github.guoxinl.protocol.analysis.utils;


import io.github.guoxinl.protocol.analysis.conf.convert.SignedChar2byteTypeConvert;
import io.github.guoxinl.protocol.analysis.model.exception.ProtocolCallbackException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Create by guoxin on 2018/7/9
 */
public class ClassUtils {
    public static Class<?> getGenericsType(Class<?> clazz) {
        Type type = clazz.getGenericInterfaces()[0];
        // 通过这个方法获取了一个Type对象，里面实际上包含了类的各种基本信息，如成员变量、方法、类名和泛型的信息...
        ParameterizedType pt   = (ParameterizedType) type;
        Type[]            args = pt.getActualTypeArguments();    //这是一包含了所有的泛型类型 信息的个数组
        return (Class<?>) args[0];
    }

    public static Object methodInvoke(Class<?> clazz, String methodName, Class<?> parameterType, Object parameter){
        Method call;
        try {
            call = clazz.getMethod(methodName, parameterType);
        } catch (NoSuchMethodException e) {
            throw new ProtocolCallbackException("未找到方法名称：" + methodName, e);
        }
        Object instance;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            // 如果Class{@link Class#newInstance}抽象类，一个接口，一个数组类，一个基本类型或void，则抛出此异常
            throw new ProtocolCallbackException("实例类型错误", e);
        } catch (IllegalAccessException e) {
            // 如果该类有无效或者不可访问构造函数
            throw new ProtocolCallbackException("该类有无效或者不可访问构造函数", e);
        }
        Object result;
        try {
            result = call.invoke(instance, parameter);
        } catch (IllegalAccessException e) {
            // 强制执行java访问控制修饰符，则不可调用
            throw new ProtocolCallbackException("强制执行java访问控制修饰符，则不可调用", e);
        } catch (InvocationTargetException e) {
            // 如果底层方法抛出异常
            throw new ProtocolCallbackException("如果底层方法抛出异常", e);
        }
        return result;
    }

    public static void main(String[] args) {
        Class<?> genericsType = getGenericsType(SignedChar2byteTypeConvert.class);
        System.out.println(genericsType);
    }
}
