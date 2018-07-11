package pub.guoxin.protocol.analysis.conf.convert;

import pub.guoxin.protocol.analysis.model.anno.Typed;
import pub.guoxin.protocol.analysis.utils.ByteUtil;

/**
 * C Signed int to Java {@link Integer}
 * <p>
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 2, numberOfBytes = Integer.SIZE / TypeConvert.BIT, description = "{@link Integer} C Signed int to Java Integer")
public class SignedInt2integerTypeConvert implements TypeConvert<Integer> {

    @Override
    public byte[] encode(Integer integer) {
        return ByteUtil.getBytes(integer);
    }

    @Override
    public Integer decode(byte[] bytes) {
        return ByteUtil.getInt(bytes);
    }

}
