package pub.guoxin.protocol.analysis.utils;

import java.io.*;

/**
 * TODO 性能待考证，但是很方便，比原生 {@link Cloneable} 方便
 * <p>
 * Create by guoxin on 2018/6/14
 */
public class ObjectClone {

    /**
     * This method makes a "deep clone" of any object it is given.
     *
     * @param object clone obj
     * @return cloned obj
     */
    public static Object deepClone(Object object) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream    objectOutputStream    = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream    objectInputStream    = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new CloneFieldException(e.getMessage(), e);
        }
    }
}

/**
 * 基本类型
 * 引用类型
 * 流的方式，瞬态方式
 */
class CloneFieldException extends RuntimeException {

    public CloneFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
