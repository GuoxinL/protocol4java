package io.github.protocol.analysis.conf.convert;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.github.protocol.analysis.model.anno.Typed;
import io.github.protocol.analysis.utils.ByteUtil;

/**
 * C Signed short to Java {@link Short}
 * <p>
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 1, numberOfBytes = Short.SIZE / TypeConvert.BIT, description = "{@link Short} C Signed short to Java Short")
public class SignedShort2shortTypeConvert implements TypeConvert<Short> {

    @Override
    public ByteBuf encode(Short s) {
        ByteBuf byteBuf = Unpooled.copyShort(s);
        return byteBuf;
    }

    @Override
    public Short decode(ByteBuf bytes) {
        short aShort = bytes.getShort(0);
        return aShort;
    }

}
