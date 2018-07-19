package pub.guoxin.protocol.samples;

import pub.guoxin.protocol.analysis.conf.register.adapter.ProtocolEntityRegisterConfigureAdapter;
import pub.guoxin.protocol.analysis.model.entity.DataProtocol;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Create by guoxin on 2018/6/14
 */
public class Application {

    public static void main(String[] args) throws IllegalAccessException {

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

        System.out.println("ggggggggggggggggggggggggggggggggggggggggggggggggg");
        ProtocolEntityRegisterConfigureAdapter registerConfigureAdapter = new ProtocolEntityRegisterConfigureAdapterImpl();
        registerConfigureAdapter.register(null);
        UpgradeProtocol upgradeProtocol = new UpgradeProtocol();
        upgradeProtocol.setEee(new short[] {1,2,3,4,5});
        upgradeProtocol.setCcc(new int[]{5,4,3,2,1});
        upgradeProtocol.setBbb("bbbbbbbbbbbb");
        upgradeProtocol.setDdd(new String[] {"dsa","aaa","bbb"});
        upgradeProtocol.setAaa("321312k321312");
        DataProtocol dataProtocol1 = new DataProtocol(upgradeProtocol);
        byte[]       serialization = dataProtocol1.serialization();
        System.out.println(Arrays.toString(serialization));
        DataProtocol dataProtocol2 = new DataProtocol(serialization);
        System.out.println(dataProtocol2);
    }
}
