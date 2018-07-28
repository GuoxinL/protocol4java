package io.github.guoxinl.protocol.analysis.utils;

import com.google.common.base.Strings;

import java.nio.charset.Charset;
import java.util.Arrays;


/**
 * Java基本数据类型和byte数组相互转化
 * <p>
 * Create by guoxin on 2018/6/26
 */
public class ByteUtil {


    public static byte[] getBytes(boolean data) {
        return new byte[]{(data) ? (byte) 1 : (byte) 0};
    }

    public static byte[] getBytes(short data) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data & 0xff00) >> 8);
        return bytes;
    }

    public static byte[] getBytes(char data) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (data);
        bytes[1] = (byte) (data >> 8);
        return bytes;
    }

    public static byte[] getBytes(int data) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data & 0xff00) >> 8);
        bytes[2] = (byte) ((data & 0xff0000) >> 16);
        bytes[3] = (byte) ((data & 0xff000000) >> 24);
        return bytes;
    }

    public static byte[] getBytes(long data) {
        byte[] bytes = new byte[8];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data >> 8) & 0xff);
        bytes[2] = (byte) ((data >> 16) & 0xff);
        bytes[3] = (byte) ((data >> 24) & 0xff);
        bytes[4] = (byte) ((data >> 32) & 0xff);
        bytes[5] = (byte) ((data >> 40) & 0xff);
        bytes[6] = (byte) ((data >> 48) & 0xff);
        bytes[7] = (byte) ((data >> 56) & 0xff);
        return bytes;
    }

    public static byte[] getBytes(float data) {
        int intBits = Float.floatToIntBits(data);
        return getBytes(intBits);
    }

    public static byte[] getBytes(double data) {
        long intBits = Double.doubleToLongBits(data);
        return getBytes(intBits);
    }

    public static byte[] getBytes(String data, String charsetName) {
        Charset charset = Charset.forName(charsetName);
        return data.getBytes(charset);
    }

    public static byte[] getBytes(String data) {
        return getBytes(data, "GBK");
    }

    public static short getShort(byte[] bytes) {
        return (short) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
    }

    public static char getChar(byte[] bytes) {
        return (char) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
    }

    public static int getInt(byte[] bytes) {
        return (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)) | (0xff0000 & (bytes[2] << 16))
                | (0xff000000 & (bytes[3] << 24));
    }

    public static long getLong(byte[] bytes) {
        return (0xffL & (long) bytes[0]) | (0xff00L & ((long) bytes[1] << 8)) | (0xff0000L & ((long) bytes[2] << 16))
                | (0xff000000L & ((long) bytes[3] << 24)) | (0xff00000000L & ((long) bytes[4] << 32))
                | (0xff0000000000L & ((long) bytes[5] << 40)) | (0xff000000000000L & ((long) bytes[6] << 48))
                | (0xff00000000000000L & ((long) bytes[7] << 56));
    }

    public static float getFloat(byte[] bytes) {
        return Float.intBitsToFloat(getInt(bytes));
    }

    public static double getDouble(byte[] bytes) {
        long l = getLong(bytes);
        System.out.println(l);
        return Double.longBitsToDouble(l);
    }

    public static String getString(byte[] bytes, Charset charset) {
        return new String(bytes, charset);
    }

    public static boolean getBoolean(byte[] bytes) {
        if (bytes.length != 1) {
            throw new IllegalArgumentException("长度过长");
        }
        return bytes[0] != 0;

    }

    public static String getString(byte[] bytes) {
        return getString(bytes, Charset.defaultCharset());
    }

    public static String bytes2hex(byte[] bytes) {
        StringBuilder buff = new StringBuilder();
        for (byte aByte : bytes) {
            String s = Integer.toHexString(aByte);
            if (s.length() == 1) { // 转换后如果是个位数则前面补零
                buff.append(0);
                buff.append(s);
            } else {
                buff.append(s);
            }

        }
        return buff.toString();
    }

    public static byte[] hex2bytes(String hexString) {
        if (Strings.isNullOrEmpty(hexString))
            return null;
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() >> 1];
        int          index     = 0;
        for (int i = 0; i < hexString.length(); i++) {
            if (index > hexString.length() - 1)
                return byteArray;
            byte highDit = (byte) (Character.digit(hexString.charAt(index), 16) & 0xFF);
            byte lowDit  = (byte) (Character.digit(hexString.charAt(index + 1), 16) & 0xFF);
            byteArray[i] = (byte) (highDit << 4 | lowDit);
            index += 2;
        }
        return byteArray;
    }

    public static void main(String[] args) {
        short  s     = 1;
        byte[] bytes = getBytes(s);
//        short s = 122;
//        int   i = 122;
//        long  l = 1222222;
//
//        char c = 'a';
//
//        float  f = 122.22f;
//        double d = 122.22;
//
//        String string = "我是好孩子";
//        System.out.println(s);
//        System.out.println(i);
//        System.out.println(l);
//        System.out.println(c);
//        System.out.println(f);
//        System.out.println(d);
//        System.out.println(string);
//
//        System.out.println("**************");
//
//        System.out.println(getShort(getBytes(s)));
//        System.out.println(getInt(getBytes(i)));
//        System.out.println(getLong(getBytes(l)));
//        System.out.println(getChar(getBytes(c)));
//        System.out.println(getFloat(getBytes(f)));
//        System.out.println(getDouble(getBytes(d)));
//        System.out.println(getString(getBytes(string)));

        byte[] bytes1 = new byte[]{127, 127, 2, 3};
        String s1     = ByteUtil.bytes2hex(bytes1);
        System.out.println(s1);
        byte[] bytes2 = ByteUtil.hex2bytes(s1);
        System.out.println(Arrays.toString(bytes2));
    }
}