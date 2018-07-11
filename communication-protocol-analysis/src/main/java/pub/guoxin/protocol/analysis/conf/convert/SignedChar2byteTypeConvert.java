package pub.guoxin.protocol.analysis.conf.convert;

import pub.guoxin.protocol.analysis.model.anno.Typed;
import pub.guoxin.protocol.analysis.utils.ByteUtil;

/**
 * C Signed char to Java {@link Byte}
 * <p>
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 0, numberOfBytes = Byte.SIZE / TypeConvert.BIT, description = "{@link Byte} C Signed char to Java Byte")
public class SignedChar2byteTypeConvert implements TypeConvert<Byte> {
    @Override
    public byte[] encode(Byte b) {
        return ByteUtil.getBytes(b);
    }

    @Override
    public Byte decode(byte[] bytes) {
        return bytes[0];
    }
}
