package pub.guoxin.protocol.analysis.utils;

/**
 * Created by guoxin on 17-10-18.
 */
public class BytesUtils {

    /**
     *
     * @param data
     * @param begin
     * @param end
     * @return new byte string
     * @throws IllegalArgumentException
     */
    public static String subStringByIndex(String data, int begin, int end) throws IllegalArgumentException {
        int length = end - begin;
        if (data.length() / 2 < length) {
            throw new IllegalArgumentException("The length of an `old` cannot be less than a `end - begin`");
        }
        return data.substring(begin * 2, end * 2);
    }

    public static byte[] createEmptyByteArray(int begin, int end){
        return new byte[end - begin];
    }
    /**
     *
     * @param old
     * @param begin
     * @param end
     * @return
     * @throws IllegalArgumentException
     */
    public static byte[] createByteArray(byte[] old, int begin, int end) throws IllegalArgumentException {
        int length = end - begin;
        if (old.length < length) {
            throw new IllegalArgumentException("The length of an `old` cannot be less than a `end - begin`");
        }
        byte[] nevv = new byte[length];
        int env = 0;
        for (int i = begin; i < end; i++) {
            nevv[env] = old[i];
            env++;
        }
        return nevv;
    }

    private static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static String toBinaryString(byte b) {
        int u = toUnsigned(b);
        return new String(new char[]{
                DIGITS[(u >>> 7) & 0x1],
                DIGITS[(u >>> 6) & 0x1],
                DIGITS[(u >>> 5) & 0x1],
                DIGITS[(u >>> 4) & 0x1],
                DIGITS[(u >>> 3) & 0x1],
                DIGITS[(u >>> 2) & 0x1],
                DIGITS[(u >>> 1) & 0x1],
                DIGITS[u & 0x1]
        });
    }

    public static String toBinaryString(byte... bytes) {
        char[] buffer = new char[bytes.length * 8];
        for (int i = 0, j = 0; i < bytes.length; ++i) {
            int u = toUnsigned(bytes[i]);
            buffer[j++] = DIGITS[(u >>> 7) & 0x1];
            buffer[j++] = DIGITS[(u >>> 6) & 0x1];
            buffer[j++] = DIGITS[(u >>> 5) & 0x1];
            buffer[j++] = DIGITS[(u >>> 4) & 0x1];
            buffer[j++] = DIGITS[(u >>> 3) & 0x1];
            buffer[j++] = DIGITS[(u >>> 2) & 0x1];
            buffer[j++] = DIGITS[(u >>> 1) & 0x1];
            buffer[j++] = DIGITS[u & 0x1];
        }
        return new String(buffer);
    }


    public static String toHexString(byte... bytes) {
        char[] buffer = new char[bytes.length * 2];
        for (int i = 0, j = 0; i < bytes.length; ++i) {
            int u = toUnsigned(bytes[i]);
            buffer[j++] = DIGITS[u >>> 4];
            buffer[j++] = DIGITS[u & 0xf];
        }
        return new String(buffer);
    }

    public static String toHexString(byte b) {
        int u = toUnsigned(b);
        return new String(new char[]{
                DIGITS[u >>> 4],
                DIGITS[u & 0xf]
        });
    }

    private static int toUnsigned(byte b) {
        return b < 0 ? b + 256 : b;
    }

    public static void main(String[] args) {
//        byte[] old = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c,};
//        byte[] byteArray = createByteArray(old, CDI.ID, CDI.BATTERY_LEVEL);
//        System.out.println(Arrays.toString(byteArray));
        String data = "0102030405060708090a0b0c0d0e0f";
        String stringArray = subStringByIndex(data, 0, 4);
        System.out.println(stringArray);
    }

    public static String binary2Hex(String binary) {
        if (binary == null || binary.equals("") || binary.length() % 8 != 0)
            return null;
        StringBuffer tmp = new StringBuffer();
        int iTmp = 0;
        for (int i = 0; i < binary.length(); i += 4) {
            iTmp = 0;
            for (int j = 0; j < 4; j++) {
                iTmp += Integer.parseInt(binary.substring(i + j, i + j + 1)) << (4 - j - 1);
            }
            tmp.append(Integer.toHexString(iTmp));
        }
        return tmp.toString();
    }
}
