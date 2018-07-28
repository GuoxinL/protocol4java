package io.github.guoxinl.protocol.analysis.conf.convert;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.github.guoxinl.protocol.analysis.model.anno.Typed;

/**
 * C Signed double to Java {@link Double}
 * <p>
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 5, numberOfBytes = Double.SIZE / TypeConvert.BIT, description = "{@link Double} C Signed double to Java Double")
public class DoubleTypeConvert implements TypeConvert<Double> {

    @Override
    public ByteBuf encode(Double d) {
        ByteBuf byteBuf = Unpooled.copyDouble(d);
        return byteBuf;
    }

    @Override
    public Double decode(ByteBuf byteBuf) {
        double aDouble = byteBuf.getDouble(0);
        return aDouble;
    }

    public static void main(String[] args) {
        double d = 11111111111.1111111111111;
        ByteBuf byteBuf = Unpooled.copyDouble(d);
        double  aDouble = byteBuf.getDouble(0);
        System.out.println(aDouble);
        assert d == aDouble;
    }
}
