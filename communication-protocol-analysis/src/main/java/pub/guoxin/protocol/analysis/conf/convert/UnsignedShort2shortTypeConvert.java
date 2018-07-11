package pub.guoxin.protocol.analysis.conf.convert;

import pub.guoxin.protocol.analysis.model.anno.Typed;
import pub.guoxin.protocol.analysis.utils.ByteUtil;

/**
 * C Unsigned short to Java {@link Short}
 * <p>
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 11, numberOfBytes = Short.SIZE / TypeConvert.BIT, description = "{@link Short} C Unsigned short to Java Short")
public class UnsignedShort2shortTypeConvert implements TypeConvert<Short> {

    /**
     * TODO Unsigned
     */
    @Override
    public byte[] encode(Short s) {
        return ByteUtil.getBytes(s);
    }

    @Override
    public Short decode(byte[] bytes) {
        return ByteUtil.getShort(bytes);
    }

}
