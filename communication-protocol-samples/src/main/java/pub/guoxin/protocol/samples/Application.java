package pub.guoxin.protocol.samples;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Create by guoxin on 2018/6/14
 */
public class Application {

    public static void main(String[] args) {
        Annotation[] annotations = UpgradeProtocol.class.getAnnotations();
        System.out.println(Arrays.toString(annotations));
        UpgradeProtocol upgradeProtocol = new UpgradeProtocol();
        Object          upgradeProtocol1 = upgradeProtocol;
        for (Field field : upgradeProtocol1.getClass().getDeclaredFields()) {
            Annotation[] annotations1 = field.getAnnotations();
            for (Annotation annotation : annotations1) {

            }
        }
    }
}
