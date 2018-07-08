package pub.guoxin.protocol.samples;

/**
 * Create by guoxin on 2018/7/8
 */
public class Test {
    public static void main(String[] args) {
        byte[] bytes1 = new byte[]{1,2,3,4,5,6};
        byte[] bytes2 = new byte[]{7,8,9,10,11,12};
        Class<? extends byte[]> aClass = bytes2.getClass();
        Class<?> componentType = aClass.getComponentType();
        System.out.println(componentType);
//        byte[] bytes3 = Array.newInstance().;
//
//        System.arraycopy(bytes1, 0, bytes2);
    }
}
