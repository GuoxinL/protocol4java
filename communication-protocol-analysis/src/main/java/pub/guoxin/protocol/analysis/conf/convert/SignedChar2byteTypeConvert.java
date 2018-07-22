package pub.guoxin.protocol.analysis.conf.convert;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import pub.guoxin.protocol.analysis.model.anno.Typed;

/**
 * C Signed char to Java {@link Byte}
 * <p>
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 0, numberOfBytes = Byte.SIZE / TypeConvert.BIT, description = "{@link Byte} C Signed char to Java Byte")
public class SignedChar2byteTypeConvert implements TypeConvert<Byte> {
    @Override
    public ByteBuf encode(Byte b) {
        ByteBuf byteBuf = Unpooled.copiedBuffer(new byte[]{b});
        return byteBuf;
    }

    @Override
    public Byte decode(ByteBuf byteBuf) {
        byte aByte = byteBuf.getByte(0);
        return aByte;
    }
}
