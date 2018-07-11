package pub.guoxin.protocol.analysis.conf.convert;

import pub.guoxin.protocol.analysis.model.anno.Typed;
import pub.guoxin.protocol.analysis.utils.ByteUtil;

/**
 * C Signed float to Java {@link Boolean}
 * <p>
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 6, numberOfBytes = 1, description = "{@link Boolean} C Signed char to Java Boolean")
public class BooleanTypeConvert implements TypeConvert<Boolean> {

    @Override
    public byte[] encode(Boolean integer) {
        return ByteUtil.getBytes(integer);
    }

    @Override
    public Boolean decode(byte[] bytes) {
        return ByteUtil.getBoolean(bytes);
    }

}
