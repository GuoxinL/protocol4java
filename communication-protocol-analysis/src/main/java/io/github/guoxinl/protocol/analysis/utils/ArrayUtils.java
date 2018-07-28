package io.github.guoxinl.protocol.analysis.utils;

import java.lang.reflect.Array;
import java.util.Objects;

/**
 * Create by guoxin on 2018/7/8
 */
public class ArrayUtils {

    /**
     * 截取arr数组下标从{@code limit}开始，截取{@code offset}个
     *
     * @param arr    原数组
     * @param limit  开始下标
     * @param offset 截取数量
     * @return 截取数组
     */
    public static <T> T[] subArray(T[] arr, int limit, int offset) {
        if (isEmpty(arr)) {
            throw new NullPointerException("null");
        }
        if ((arr.length - limit) < offset) {
            throw new IllegalArgumentException("Array length less than offset");
        }
        @SuppressWarnings("unchecked")
        T[] result = (T[]) Array.newInstance(arr.getClass().getComponentType(), offset);
        int j = 0;
        for (int i = limit; i < offset; i++) {
            result[j] = arr[i];
            j++;
        }
        return result;
    }

    /**
     * 截取arr数组下标从{@code limit}开始，截取{@code offset}个
     * 默认从0开始截取
     *
     * @param arr    原数组
     * @param offset 截取数量
     * @return 截取数组
     */
    public static <T> T[] subArray(T[] arr, int offset) {
        return subArray(arr, 0, offset);
    }

    public static byte[] subArray(byte[] arr, int limit, int offset) {
        if (isEmpty(arr)) {
            throw new IllegalArgumentException("Array is empty");
        }
        if ((arr.length - limit) < offset) {
            throw new IllegalArgumentException("Array length less than offset");
        }
        @SuppressWarnings("unchecked")
        byte[] result = (byte[]) Array.newInstance(arr.getClass().getComponentType(), offset);
        int j = 0;
        for (int i = limit; i < offset; i++) {
            result[j] = arr[i];
            j++;
        }
        return result;
    }

    public static byte[] subArray(byte[] arr, int offset) {
        return subArray(arr, 0, offset);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] merge(T[] arr1, T[] arr2) {
        if (isEmpty(arr1) && isEmpty(arr2)) {
            // 两个数组都为空时，创建空数组
            return (T[]) Array.newInstance(arr1.getClass().getComponentType());
        } else if (isEmpty(arr1) && !isEmpty(arr2)) {
            // arr1为空时，创建arr2 clone
            return arr2.clone();
        } else if (!isEmpty(arr1) && isEmpty(arr2)) {
            // arr2为空时，创建arr1 clone
            return arr1.clone();
        }
        T[] result = (T[]) Array.newInstance(arr1.getClass().getComponentType(), arr1.length + arr2.length);
        System.arraycopy(arr1, 0, result, 0, arr1.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
        return result;
    }

    @SuppressWarnings("unchecked")
    public static byte[] merge(byte[] arr1, byte[] arr2) {
        if (isEmpty(arr1) && isEmpty(arr2)) {
            // 两个数组都为空时，创建空数组
            return (byte[]) Array.newInstance(arr1.getClass().getComponentType());
        } else if (isEmpty(arr1) && !isEmpty(arr2)) {
            // arr1为空时，创建arr2 clone
            return arr2.clone();
        } else if (!isEmpty(arr1) && isEmpty(arr2)) {
            // arr2为空时，创建arr1 clone
            return arr1.clone();
        }
        byte[] result = (byte[]) Array.newInstance(arr1.getClass().getComponentType(), arr1.length + arr2.length);
        System.arraycopy(arr1, 0, result, 0, arr1.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
        return result;
    }


    public static <T> boolean isEmpty(T[] arr) {
        return Objects.isNull(arr) || arr.length == 0;
    }

    public static boolean isEmpty(byte[] arr) {
        return Objects.isNull(arr) || arr.length == 0;
    }

    public static boolean isEmpty(short[] arr) {
        return Objects.isNull(arr) || arr.length == 0;
    }

    public static boolean isEmpty(char[] arr) {
        return Objects.isNull(arr) || arr.length == 0;
    }

    public static boolean isEmpty(int[] arr) {
        return Objects.isNull(arr) || arr.length == 0;
    }

    public static boolean isEmpty(long[] arr) {
        return Objects.isNull(arr) || arr.length == 0;
    }

    public static boolean isEmpty(double[] arr) {
        return Objects.isNull(arr) || arr.length == 0;
    }

    public static boolean isEmpty(float[] arr) {
        return Objects.isNull(arr) || arr.length == 0;
    }

    public static boolean isEmpty(boolean[] arr) {
        return Objects.isNull(arr) || arr.length == 0;
    }


    public static void main(String[] args) {
    }

}

