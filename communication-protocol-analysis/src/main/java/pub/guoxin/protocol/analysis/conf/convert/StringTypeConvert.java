package pub.guoxin.protocol.analysis.conf.convert;

import pub.guoxin.protocol.analysis.model.anno.Typed;
import pub.guoxin.protocol.analysis.utils.ByteUtil;

/**
 * C char array to Java {@link String}
 *
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 7, description = "{@link String} C char array to Java String")
public class StringTypeConvert implements TypeConvert<String> {

    @Override
    public byte[] encode(String s) {
        return ByteUtil.getBytes(s);
    }

    @Override
    public String decode(byte[] bytes) {
        return ByteUtil.getString(bytes);
    }

}
