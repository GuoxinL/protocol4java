package pub.guoxin.protocol.analysis.utils;

import java.lang.reflect.Array;
import java.util.Objects;

/**
 * Create by guoxin on 2018/7/8
 */
public class ArrayUtils {
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
    public static  byte[] merge(byte[] arr1, byte[] arr2) {
        if (isEmpty(arr1) && isEmpty(arr2)) {
            // 两个数组都为空时，创建空数组
            return (byte[])Array.newInstance(arr1.getClass().getComponentType());
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
