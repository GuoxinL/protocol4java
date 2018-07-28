package io.github.guoxinl.protocol.analysis.conf.convert;

import io.github.guoxinl.protocol.analysis.model.anno.Typed;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.github.guoxinl.protocol.analysis.utils.UnsignedLengthValidate;

/**
 * C Unsigned int to Java {@link Integer}
 * <p>
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 12, numberOfBytes = Integer.SIZE / TypeConvert.BIT, description = "{@link Integer} C Unsigned int to Java Integer")
public class UnsignedInt2integerTypeConvert implements TypeConvert<Long> {

    @Override
    public ByteBuf encode(Long unsignedInt) {
        UnsignedLengthValidate.validateUnsignedInt(unsignedInt);
        int     signedInt = (int) ((long) unsignedInt);
        ByteBuf byteBuf   = Unpooled.copyInt(signedInt);
        return byteBuf;
    }

    @Override
    public Long decode(ByteBuf bytes) {
        long unsignedInt = bytes.getUnsignedInt(0);
        return unsignedInt;
    }

}
