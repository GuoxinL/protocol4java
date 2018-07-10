package pub.guoxin.protocol.samples;

import pub.guoxin.protocol.analysis.conf.convert.IntegerTypeConvert;
import pub.guoxin.protocol.analysis.conf.convert.StringTypeConvert;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Create by guoxin on 2018/6/14
 */
public class Application {

    public static void main(String[] args) throws IllegalAccessException {

        byte[]     bytes  = new byte[]{3, 2, 1, 3, 2, 1, 9, 8, 3, 7, 1, 2, 8, 9, 3, 7, 1, 2, 8, 9, 3, 7, 8, 1, 2, 9, 7, 3, 1, 2};
        ByteBuffer wrap   = ByteBuffer.wrap(bytes);
        byte[]     abytes = new byte[4];

        wrap.get(abytes);
        System.out.println(Arrays.toString(abytes));
        wrap.get(abytes);

        System.out.println(Arrays.toString(abytes));

        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        UpgradeProtocol upgradeProtocol = new UpgradeProtocol();
        upgradeProtocol.setAaa("321312");
        upgradeProtocol.setBbb("zncm,asnda");
        upgradeProtocol.setCcc(new int[]{1, 2, 23, 3, 4, 5, 5, 6});
        upgradeProtocol.setEee(new short[]{1, 2, 23, 3, 4, 5, 5, 6});
        upgradeProtocol.setDdd(new String[]{"dsa","dsa"});
        Class<? extends UpgradeProtocol> aClass = upgradeProtocol.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            Class<?> type = field.getType();
            System.out.println(type);
            field.setAccessible(true);
            Object o = field.get(upgradeProtocol);
            System.out.println(o.toString());
        }

//        Class<IntegerTypeConvert> integerTypeConvertClass = IntegerTypeConvert.class;
////        integerTypeConvertClass.getMethod()
    }
}
