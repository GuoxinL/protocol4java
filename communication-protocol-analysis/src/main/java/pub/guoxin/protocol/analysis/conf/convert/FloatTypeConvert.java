package pub.guoxin.protocol.analysis.conf.convert;

import pub.guoxin.protocol.analysis.model.anno.Typed;
import pub.guoxin.protocol.analysis.utils.ByteUtil;

/**
 * C Signed float to Java Float
 * <p>
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 4, numberOfBytes = Float.SIZE / TypeConvert.BIT, description = "{@link Float} C Signed float to Java Float")
public class FloatTypeConvert implements TypeConvert<Float> {

    @Override
    public byte[] encode(Float f) {
        return ByteUtil.getBytes(f);
    }

    @Override
    public Float decode(byte[] bytes) {
        return ByteUtil.getFloat(bytes);
    }
}
