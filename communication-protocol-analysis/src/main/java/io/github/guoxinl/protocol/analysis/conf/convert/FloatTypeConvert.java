package io.github.guoxinl.protocol.analysis.conf.convert;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.github.guoxinl.protocol.analysis.model.anno.Typed;

/**
 * C Signed float to Java Float
 * <p>
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 4, numberOfBytes = Float.SIZE / TypeConvert.BIT, description = "{@link Float} C Signed float to Java Float")
public class FloatTypeConvert implements TypeConvert<Float> {

    @Override
    public ByteBuf encode(Float f) {
        ByteBuf byteBuf = Unpooled.copyFloat(f);
        return byteBuf;
    }

    @Override
    public Float decode(ByteBuf byteBuf) {
        float aFloat = byteBuf.getFloat(0);
        return aFloat;
    }
}
