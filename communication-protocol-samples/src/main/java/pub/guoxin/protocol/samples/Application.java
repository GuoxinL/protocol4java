package pub.guoxin.protocol.samples;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Create by guoxin on 2018/6/14
 */
public class Application {

    public static void main(String[] args) throws IllegalAccessException {

//        byte[]     bytes  = new byte[]{3, 2, 1, 3, 2, 1, 9, 8, 3, 7, 1, 2, 8, 9, 3, 7, 1, 2, 8, 9, 3, 7, 8, 1, 2, 9, 7, 3, 1, 2};
//        ByteBuffer wrap   = ByteBuffer.wrap(bytes);
//        byte[]     abytes = new byte[4];
//
//        wrap.get(abytes);
//        System.out.println(Arrays.toString(abytes));
//        wrap.get(abytes);
//
//        System.out.println(Arrays.toString(abytes));
//
//        System.out.println("~~~~~~~~~~~~~~~~~~~~");
//        UpgradeProtocol upgradeProtocol = new UpgradeProtocol();
//        upgradeProtocol.setAaa("321312");
//        upgradeProtocol.setBbb("zncm,asnda");
//        upgradeProtocol.setCcc(new int[]{1, 2, 23, 3, 4, 5, 5, 6});
//        upgradeProtocol.setEee(new short[]{1, 2, 23, 3, 4, 5, 5, 6});
//        upgradeProtocol.setDdd(new String[]{"dsa","dsa"});
//        Class<? extends UpgradeProtocol> aClass = upgradeProtocol.getClass();
//        for (Field field : aClass.getDeclaredFields()) {
//            field.setAccessible(true);
//            Object o = field.get(upgradeProtocol);
//            System.out.println(o.toString());
//            if (field.getType().isArray()) {
//                Class<?> componentType = field.getType().getComponentType();
//                Class<?> type = field.getType();
//                System.out.println(componentType);
//                System.out.println(type);
//            } else {
//                Class<?> type = field.getType();
//                System.out.println(type);
//            }
//        }

//        Class<SignedInt2integerTypeConvert> integerTypeConvertClass = SignedInt2integerTypeConvert.class;
////        integerTypeConvertClass.getMethod()

        System.out.println(byte.class);
        System.out.println(short.class);
        System.out.println(int.class);
        System.out.println(long.class);
        System.out.println(float.class);
        System.out.println(double.class);
        System.out.println(char.class);
        System.out.println(boolean.class);
        System.out.println("--------------------------------------------------");
        System.out.println(byte[].class);
        System.out.println(short[].class);
        System.out.println(int[].class);
        System.out.println(long[].class);
        System.out.println(float[].class);
        System.out.println(double[].class);
        System.out.println(char[].class);
        System.out.println(boolean[].class);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(Byte.class);
        System.out.println(Short.class);
        System.out.println(Integer.class);
        System.out.println(Long.class);
        System.out.println(Float.class);
        System.out.println(Double.class);
        System.out.println(Character.class);
        System.out.println(Boolean.class);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(Byte[].class);
        System.out.println(Short[].class);
        System.out.println(Integer[].class);
        System.out.println(Long[].class);
        System.out.println(Float[].class);
        System.out.println(Double[].class);
        System.out.println(Character[].class);
        System.out.println(Boolean[].class);

        System.out.println("--------------------------------------------------");

        System.out.println(byte.class.getComponentType());
        System.out.println(short.class.getComponentType());
        System.out.println(int.class.getComponentType());
        System.out.println(long.class.getComponentType());
        System.out.println(float.class.getComponentType());
        System.out.println(double.class.getComponentType());
        System.out.println(char.class.getComponentType());
        System.out.println(boolean.class.getComponentType());

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println(byte[].class.getComponentType());
        System.out.println(short[].class.getComponentType());
        System.out.println(int[].class.getComponentType());
        System.out.println(long[].class.getComponentType());
        System.out.println(float[].class.getComponentType());
        System.out.println(double[].class.getComponentType());
        System.out.println(char[].class.getComponentType());
        System.out.println(boolean[].class.getComponentType());

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        System.out.println(Byte[].class.getComponentType());
        System.out.println(Short[].class.getComponentType());
        System.out.println(Integer[].class.getComponentType());
        System.out.println(Long[].class.getComponentType());
        System.out.println(Float[].class.getComponentType());
        System.out.println(Double[].class.getComponentType());
        System.out.println(Character[].class.getComponentType());
        System.out.println(Boolean[].class.getComponentType());
    }
}
