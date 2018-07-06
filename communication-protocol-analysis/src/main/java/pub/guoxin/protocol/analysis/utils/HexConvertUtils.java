package pub.guoxin.protocol.analysis.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import pub.guoxin.protocol.analysis.model.TypeClass;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Arrays;
import java.util.Objects;

/**
 * 小端模式 十六进制转基本类型
 * Created by guoxin on 17-10-18.
 */
public class HexConvertUtils {

    public static Object getFieldValueByDataType(TypeClass typeClass, String hexString) throws DecoderException {
        switch (typeClass) {
            case Byte:
                return hexString2Byte(hexString);
            case Short:
                return hexString2Short(hexString);
            case Integer:
                return hexString2Int(hexString);
            case Long:
                return hexString2Long(hexString);
            case Float:
                return hexString2Float(hexString);
            case Double:
                return hexString2Double(hexString);
            case Boolean:
                return hexString2Boolean(hexString);
            case String:
                return hexString2String(hexString);
            default:
                throw new IllegalArgumentException("不支持该类型");
        }
    }

    public static String getHexStringByDataType(TypeClass typeClass, Object obj) {
        switch (typeClass) {
            case Byte:
                return byte2hexString((byte) obj);
            case Short:
                return short2hexString((Short) obj);
            case Integer:
                return integer2hexString((Integer) obj);
            case Long:
                return long2hexString((Long) obj);
            case Float:
                return float2hexString((Float) obj);
            case Double:
                return double2hexString((Double) obj);
            case Boolean:
                return boolean2hexString((Boolean) obj);
            case String:
                return string2hexString((String) obj);
            default:
                throw new IllegalArgumentException("不支持该类型");
        }
    }

    public static String byteArrayToString(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        CharsetDecoder decoder = Charset.forName("Cp1252").newDecoder();
//        用这个的话，只能输出来一次结果，第二次显示为空
//         charBuffer = decoder.decode(byteBuffer);
        CharBuffer charBuffer = null;
        try {
            charBuffer = decoder.decode(byteBuffer.asReadOnlyBuffer());
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(charBuffer);
        return charBuffer.toString();
    }

    public static short byteArrayToShort(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return byteBuffer.getShort();
    }

    public static byte byteArrayToByte(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return byteBuffer.get();
    }

    public static int byteArrayToInt(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return byteBuffer.getInt();
    }

    public static long byteArrayToLong(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return byteBuffer.getLong();
    }

    public static float byteArrayToFloat(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return byteBuffer.getFloat();
    }

    public static double byteArrayToDouble(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return byteBuffer.getDouble();
    }

    public static char byteArrayToChar(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return byteBuffer.getChar();
    }

    public static char hexString2Char(String hexString) throws DecoderException {
        byte[] bytes = Hex.decodeHex(hexString.toCharArray());
        return byteArrayToChar(bytes);
    }

    public static byte[] hexString2bytes(String hexString) throws DecoderException {
        return Hex.decodeHex(hexString.toCharArray());
    }

    public static double hexString2Double(String hexString) throws DecoderException {
        byte[] bytes = Hex.decodeHex(hexString.toCharArray());
        return byteArrayToDouble(bytes);
    }

    public static float hexString2Float(String hexString) throws DecoderException {
        byte[] bytes = Hex.decodeHex(hexString.toCharArray());
        return byteArrayToFloat(bytes);
    }

    public static short hexString2Short(String hexString) throws DecoderException {
        byte[] bytes = Hex.decodeHex(hexString.toCharArray());
        return byteArrayToShort(bytes);
    }

    public static byte hexString2Byte(String hexString) throws DecoderException {
        byte[] bytes = Hex.decodeHex(hexString.toCharArray());
        if (bytes.length > 2) {
            throw new DecoderException("HexString too long");
        }
        return bytes[0];
    }

    public static int hexString2Int(String hexString) throws DecoderException {
        byte[] bytes = Hex.decodeHex(hexString.toCharArray());
        return byteArrayToInt(bytes);
    }

    public static boolean hexString2Boolean(String hexString) throws DecoderException {
        byte[] bytes = Hex.decodeHex(hexString.toCharArray());
        if (bytes.length != 1) {
            throw new DecoderException("长度过长");
        }
        return bytes[0] != 0;
    }

    public static long hexString2Long(String hexString) throws DecoderException {
        byte[] bytes = Hex.decodeHex(hexString.toCharArray());
        return byteArrayToLong(bytes);
    }

    public static String hexString2String(String hexString) throws DecoderException {
        byte[] bytes = Hex.decodeHex(hexString.toCharArray());
        return byteArrayToString(bytes);
    }

    public static String integer2hexString(Integer integer) {
        return Integer.toHexString(integer);
    }

    public static String short2hexString(Short aShort) {
        return Integer.toHexString(-aShort & 0xffff);
    }

    public static String byte2hexString(byte aByte) {
        String hexstring = Integer.toHexString(aByte & 0xFF).toUpperCase();
        if (hexstring.length() == 1) {
            hexstring = "0" + hexstring;
        }
        return hexstring;
    }

    public static String long2hexString(long l) {
        return Long.toHexString(l);
    }

    public static String string2hexString(String s) {
        byte[] bytes     = s.getBytes();
        String hexString = BytesUtils.toHexString(bytes);
        return hexString;
    }

    public static String string2hexString(String s, int length) {
        BigInteger bigInteger = new BigInteger(1, s.getBytes(/*YOUR_CHARSET?*/));
        System.out.println(bigInteger);
        return String.format("%0" + (length * 2) + "x", bigInteger);
    }

    public static String double2hexString(double d) {
        return Double.toHexString(d);
    }

    public static String float2hexString(float f) {
        return Float.toHexString(f);
    }

    public static String boolean2hexString(boolean b) {
        return "0" + Integer.toHexString((b) ? 1 : 0);
    }

    public static void main(String[] args) throws DecoderException, CharacterCodingException {

        String hexString3 = Integer.toHexString((true) ? 1 : 0);
        System.out.println(hexString3);
        byte   fdsa       = 10;
        String hexString1 = byte2hexString(fdsa);
        String hexString2 = "0A";
        char[] chars      = hexString2.toCharArray();
        byte[] bytes      = Hex.decodeHex(chars);
        System.out.println("~~~" + Arrays.toString(bytes));

        System.out.println(hexString1);
        Byte decode = Byte.decode("127");
        System.out.println(decode);
        byte[] testfloat = {-26, 16, 0, 0, 0, 0, 0, 0};
        String hexString = BytesUtils.toHexString(testfloat);
        System.out.println(hexString);
        boolean[] m         = {true, false, true, true};
        String    binaryStr = "";
        for (boolean bit : m) {
            binaryStr = binaryStr + ((bit) ? "1" : "0");
        }
        boolean boo     = true;
        int     integer = (boo) ? 1 : 0;
        int     decimal = Integer.parseInt(binaryStr, 2);
        String  hexStr  = Integer.toHexString(integer);
        System.out.println("hexStr" + hexStr);

        byte[] testf = new byte[7];

        System.arraycopy(testfloat, 1, testf, 0, testf.length);
        System.out.println("~~~" + Arrays.toString(testf));
        String strFloat = "BD00833F";
        Float  aFloat   = hexString2Float(strFloat);
        System.out.println(aFloat);


        String strLong = "3930000000000000";
        long   l       = hexString2Long(strLong);
        System.out.println(l);

        String strDouble = "E17A14AE474D9340";
        double v         = hexString2Double(strDouble);
        System.out.println(v);

        byte[] testint = {-95, 0, 0, 0,};
        for (int i = 0; i < testint.length; i++) {
            int i1 = testint[i] & 0xFF;
            System.out.println(i1);
        }

        for (int i = 0; i < testfloat.length; i++) {
            int i1 = testfloat[i] & 0xFF;
            System.out.println("abc" + i1);
        }
        String strchar = "EF";
//        char s = hexString2Char(strchar);
//        System.out.println("string" + s);
        double  a  = 4.1;
        int     b  = 4;
        boolean b1 = a == b;
        System.out.println(b1);
        int i2 = 85 + 12;
        System.out.println(i2);
        int i = 10 / 5;
        System.out.println(i);
        int i1 = i2 % 85;
        System.out.println(i1);

        for (int j = 0; j < 3; j++) {
            System.out.println(j);
        }
    }
}
