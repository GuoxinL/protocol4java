package pub.guoxin.protocol.analysis.conf.convert;

import pub.guoxin.protocol.analysis.model.anno.Typed;
import pub.guoxin.protocol.analysis.utils.ByteUtil;

/**
 * C Signed double to Java {@link Double}
 * <p>
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 5, numberOfBytes = Double.SIZE / TypeConvert.BIT, description = "{@link Double} C Signed double to Java Double")
public class DoubleTypeConvert implements TypeConvert<Double> {

    @Override
    public byte[] encode(Double integer) {
        return ByteUtil.getBytes(integer);
    }

    @Override
    public Double decode(byte[] bytes) {
        return ByteUtil.getDouble(bytes);
    }

}
