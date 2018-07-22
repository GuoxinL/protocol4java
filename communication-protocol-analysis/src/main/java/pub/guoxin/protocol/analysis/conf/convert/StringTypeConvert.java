package pub.guoxin.protocol.analysis.conf.convert;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import pub.guoxin.protocol.analysis.model.anno.Typed;
import pub.guoxin.protocol.analysis.utils.ByteUtil;

import java.nio.charset.Charset;

/**
 * C char array to Java {@link String}
 *
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 7, description = "{@link String} C char array to Java String")
public class StringTypeConvert implements TypeConvert<String> {

    @Override
    public ByteBuf encode(String s) {
        byte[] bytes = s.getBytes(Charset.forName("utf-8"));
        return Unpooled.copiedBuffer(bytes);
    }

    @Override
    public String decode(ByteBuf byteBuf) {
        CharSequence charSequence = byteBuf.getCharSequence(0, byteBuf.capacity(), Charset.forName("utf-8"));
        return charSequence.toString();
    }

}
