package pub.guoxin.protocol.analysis.conf.convert;

/**
 * Create by guoxin on 2018/6/26
 */
public interface TypeConvert<T> {
    int BIT = 8;

    /**
     * 将{@code t}解码为字节流
     *
     * @param t obj
     * @return 字节流
     */
    byte[] encode(T t);

    /**
     * 将字节流编码为{@link T}
     *
     * @param bytes 字节流
     * @return obj
     */
    T decode(byte[] bytes);
}
