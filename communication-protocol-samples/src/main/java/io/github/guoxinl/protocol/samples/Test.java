package io.github.guoxinl.protocol.samples;

import java.lang.reflect.Field;

/**
 * Create by guoxin on 2018/7/8
 */
public class Test {
    public static void main(String[] args) {
        Field[] declaredFields = Protocol4javaExampleApplication.UpgradeProtocol.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Class<?> type = declaredField.getType();
            if (type.isArray()){
                Class<?> type1 = type.getComponentType();
                System.out.println(type1);
            }
        }
    }
}
