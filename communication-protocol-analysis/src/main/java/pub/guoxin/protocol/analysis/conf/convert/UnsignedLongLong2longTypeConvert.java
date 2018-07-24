package pub.guoxin.protocol.analysis.conf.convert;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import pub.guoxin.protocol.analysis.model.anno.Typed;
import pub.guoxin.protocol.analysis.utils.ByteUtil;

import java.math.BigInteger;

/**
 * TODO this using {@link BigInteger} implementation
 * C Unsigned long long to Java {@link Long}
 * <p>
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 13, numberOfBytes = Long.SIZE / TypeConvert.BIT, description = "{@link Long} C Unsigned Long Long to Java Long")
public class UnsignedLongLong2longTypeConvert implements TypeConvert<BigInteger> {

    private static final BigInteger TWO_64 = BigInteger.ONE.shiftLeft(64);

    @Override
    public ByteBuf encode(BigInteger bigInteger) {
        long l = bigInteger.longValue();
        ByteBuf byteBuf = Unpooled.copyLong(l);
        return byteBuf;
    }

    @Override
    public BigInteger decode(ByteBuf byteBuf) {
        long       l = byteBuf.readLong();
        BigInteger bigInteger = BigInteger.valueOf(l);
        if (bigInteger.signum() < 0) {
            bigInteger = bigInteger.add(TWO_64);
        }
        return bigInteger;
    }

}
