package io.github.protocol.analysis.conf.convert;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.github.protocol.analysis.model.anno.Typed;
import io.github.protocol.analysis.utils.ByteUtil;
import io.github.protocol.analysis.utils.UnsignedLengthValidate;

/**
 * C Unsigned char to Java {@link Short}
 * <p>
 * Length: 0 ~ 255
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 10, numberOfBytes = Byte.SIZE / TypeConvert.BIT, description = "{@link Byte} C Unsigned char to Java Byte")
public class UnsignedChar2byteTypeConvert implements TypeConvert<Short> {

    @Override
    public ByteBuf encode(Short unsignedShort) {
        UnsignedLengthValidate.validateUnsignedByte(unsignedShort);
        byte signedByte = (byte)((short)unsignedShort);

        ByteBuf byteBuf = Unpooled.copiedBuffer(new byte[]{signedByte});
        return byteBuf;
    }

    @Override
    public Short decode(ByteBuf byteBuf) {
        short unsignedByte = byteBuf.getUnsignedByte(0);
        return unsignedByte;
    }

}
