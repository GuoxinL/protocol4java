package io.github.guoxinl.protocol.samples;

import com.google.common.collect.Lists;
import io.github.guoxinl.protocol.analysis.conf.register.ProtocolEntityRegister;
import io.github.guoxinl.protocol.analysis.model.entity.DataProtocol;
import io.github.guoxinl.protocol.analysis.utils.ClassUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Create by guoxin on 2018/6/14
 */
public class Application {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        TestProtocol testProtocol = new TestProtocol();
        testProtocol.setFff(Lists.newArrayList(1,2,3,4,5,6));
        Field[] declaredFields = TestProtocol.class.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            Type     genericType  = declaredField.getGenericType();

            Class<?> type         = declaredField.getType();
            if (Collection.class.isAssignableFrom(type)) {
                System.out.println("true");
                Type     generi1cType  = declaredField.getGenericType();
                if (generi1cType instanceof Class) {
                    Class generi1cType1 = (Class) generi1cType;
                } else if (generi1cType instanceof ParameterizedType) {
                    Type[] actualTypeArguments = ((ParameterizedType) generi1cType).getActualTypeArguments();
                    Type   actualTypeArgument  = actualTypeArguments[0];
                    if (actualTypeArgument instanceof Class){
                        Class actualTypeArgument1 = (Class) actualTypeArgument;
                        System.out.println(actualTypeArgument1);
                    }
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(testProtocol);
                    Collection obj1 = (Collection) obj;
                    for (Object o : obj1) {
                        System.out.println(o);
                    }
                    System.out.println(Arrays.toString(actualTypeArguments));
                    System.out.println(actualTypeArgument);

                } else if (generi1cType instanceof GenericArrayType) {
                    ((GenericArrayType) generi1cType).getGenericComponentType();
                } else if (generi1cType instanceof TypeVariable) {
                    ((TypeVariable) generi1cType).getName();
                }
                System.out.println(generi1cType.toString());
            } else {
                System.out.println("false");
                Class<?> genericsType = ClassUtils.getGenericsType(type);
                System.out.println(genericsType.toString());
            }

        }

//        ProtocolEntityRegister register = new ProtocolEntityRegister();
//        register.register(UpgradeProtocol.class);
//
//
//
//
//
//
//
//
//        UpgradeProtocol upgradeProtocol = new UpgradeProtocol();
//        upgradeProtocol.setEee(new short[] {1,2,3,4,5});
//        upgradeProtocol.setCcc(new int[]{5,4,3,2,1});
//        upgradeProtocol.setBbb("bbbbbbbbbbbb");
//        upgradeProtocol.setDdd(new String[] {"dsa","aaa","bbb"});
//        upgradeProtocol.setAaa("aaaaaaaaa");
//        List<Integer> objects = Lists.newArrayList();
//        upgradeProtocol.setFff(objects);
//        DataProtocol dataProtocol1 = DataProtocol.convert(upgradeProtocol);
//        System.out.println(dataProtocol1);
//        ByteBuf      buffer        = Unpooled.buffer();
//        dataProtocol1.serialization(buffer);
//
//        System.out.println(Arrays.toString(ByteBufUtil.getBytes(buffer)));
//        DataProtocol dataProtocol2 = DataProtocol.analysis(buffer);
//        System.out.println(dataProtocol2);
        Collection collection = Collection.class.newInstance();

    }
}
