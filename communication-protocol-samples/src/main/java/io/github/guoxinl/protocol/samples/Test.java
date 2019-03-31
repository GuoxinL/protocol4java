package io.github.guoxinl.protocol.samples;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import lombok.*;

import java.lang.reflect.Field;
import java.util.LinkedList;

/**
 * Create by guoxin on 2018/7/8
 */
public class Test {

    public void test(){
        Field[] declaredFields = Protocol4javaSamplesApplication.UpgradeProtocol.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Class<?> type = declaredField.getType();
            if (type.isArray()){
                Class<?> type1 = type.getComponentType();
                System.out.println(type1);
            }
        }
    }
    public static void main(String[] args) {
        LinkedList<demo> linkedList = Lists.newLinkedList();
        linkedList.add(new demo("1", 1,1));
        linkedList.add(new demo("3", 3,3));
        linkedList.add(new demo("2", 2,2));

        System.out.println(linkedList.toString());
    }


    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    static class  demo {
        private String a;
        private Integer b;
        private int c;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            demo demo = (demo) o;
            return c == demo.c &&
                    Objects.equal(b, demo.b);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(b, c);
        }
    }
}
