package pub.guoxin.protocol.analysis.conf.convert;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import pub.guoxin.protocol.analysis.model.anno.Typed;

/**
 * C Signed long long to Java {@link Long}
 * <p>
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 3, numberOfBytes = Long.SIZE / TypeConvert.BIT, description = "{@link Long} C Signed Long Long to Java Long")
public class SignedLongLong2longTypeConvert implements TypeConvert<Long> {

    @Override
    public ByteBuf encode(Long l) {
        ByteBuf byteBuf = Unpooled.copyLong(l);
        return byteBuf;
    }

    @Override
    public Long decode(ByteBuf byteBuf) {
        long aLong = byteBuf.getLong(0);
        return aLong;
    }

}
