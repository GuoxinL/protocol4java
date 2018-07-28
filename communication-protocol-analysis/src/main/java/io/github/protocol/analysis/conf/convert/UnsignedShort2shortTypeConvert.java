package io.github.protocol.analysis.conf.convert;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.github.protocol.analysis.model.anno.Typed;
import io.github.protocol.analysis.utils.UnsignedLengthValidate;

/**
 * C Unsigned short to Java {@link Short}
 * <p>
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 11, numberOfBytes = Short.SIZE / TypeConvert.BIT, description = "{@link Short} C Unsigned short to Java Short")
public class UnsignedShort2shortTypeConvert implements TypeConvert<Integer> {

    @Override
    public ByteBuf encode(Integer unsignedShort) {
        UnsignedLengthValidate.validateUnsignedShort(unsignedShort);
        ByteBuf byteBuf = Unpooled.copyShort(unsignedShort);
        return byteBuf;
    }

    @Override
    public Integer decode(ByteBuf byteBuf) {
        int unsignedShort = byteBuf.getUnsignedShort(0);
        return unsignedShort;
    }

}
