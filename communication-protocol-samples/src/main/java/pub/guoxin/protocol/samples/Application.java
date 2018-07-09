package pub.guoxin.protocol.samples;

import pub.guoxin.protocol.analysis.conf.convert.StringTypeConvert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Create by guoxin on 2018/6/14
 */
public class Application {

    public static void main(String[] args) {

        byte[]     bytes = new byte[]{3, 2, 1, 3, 2, 1, 9, 8, 3, 7, 1, 2, 8, 9, 3, 7, 1, 2, 8, 9, 3, 7, 8, 1, 2, 9, 7, 3, 1, 2};
        ByteBuffer wrap  = ByteBuffer.wrap(bytes);
        byte[] abytes = new byte[4];

        wrap.get(abytes);
        System.out.println(Arrays.toString(abytes));
        wrap.get(abytes);

        System.out.println(Arrays.toString(abytes));
    }
}
