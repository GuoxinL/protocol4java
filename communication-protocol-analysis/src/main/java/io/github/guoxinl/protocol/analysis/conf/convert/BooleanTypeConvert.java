package io.github.guoxinl.protocol.analysis.conf.convert;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.github.guoxinl.protocol.analysis.model.anno.Typed;

/**
 * C Signed float to Java {@link Boolean}
 * <p>
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 6, numberOfBytes = 1, description = "{@link Boolean} C Signed char to Java Boolean")
public class BooleanTypeConvert implements TypeConvert<Boolean> {

    @Override
    public ByteBuf encode(Boolean b) {
        ByteBuf byteBuf = Unpooled.copyBoolean(b);
        return byteBuf;
    }

    @Override
    public Boolean decode(ByteBuf byteBuf) {
        boolean aBoolean = byteBuf.getBoolean(0);
        return aBoolean;
    }

}
