package io.github.guoxinl.protocol.analysis.utils;

/**
 * Create by guoxin on 2018/7/22
 */
public class UnsignedLengthValidate {

    public static void validateUnsignedByte(short s) {
        validateBoundaryValue(s, 255, 0, "Out of bounds unsigned byte:[" + s + "]");
    }

    public static void validateUnsignedShort(int i) {
        validateBoundaryValue(i, 65535, 0, "Out of bounds unsigned short:[" + i + "]");
    }

    public static void validateUnsignedInt(long i) {
        validateBoundaryValue(i, 4294967295L, 0, "Out of bounds unsigned int:[" + i + "]");
    }

    // unsigned long long so long

    public static void validateBoundaryValue(long value, long up, int down, String message) {
        if (value < down || value > up) {
            throw new IllegalArgumentException("Validate unsigned byte");
        }
    }

}
