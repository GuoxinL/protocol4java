package io.github.guoxinl.protocol.samples;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Create by guoxin on 2018/8/13
 */
public class TestHashSet {
    public static class HashEntity{
        private String name;
        private Integer age;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HashEntity that = (HashEntity) o;
            return Objects.equals(name, that.name) &&
                    Objects.equals(age, that.age);
        }

        @Override
        public int hashCode() {
            List<String> collect = new ArrayList<>();
            for (Field field : HashEntity.class.getDeclaredFields()) {
                String fieldName = field.getName();
                collect.add(fieldName);
            }
            System.out.println(collect.toString());
            return Objects.hash(collect);
        }

    }

    /**
     * 需要实现的功能：
     *
     *
     *
     * 需要一个有序不可重复集合对字段名称和类型名称进行hash，自动排序
     *
     * @param args
     */
    public static void main(String[] args) {
//        HashEntity hashEntity = new HashEntity();
//        int        i          = hashEntity.hashCode();
//        System.out.println(i);
        int i1 = new Object().hashCode();
        System.out.println("hashCode: " + i1);
    }
}
