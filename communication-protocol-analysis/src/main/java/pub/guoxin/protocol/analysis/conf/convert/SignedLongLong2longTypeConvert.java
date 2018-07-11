package pub.guoxin.protocol.analysis.conf.convert;

import pub.guoxin.protocol.analysis.model.anno.Typed;
import pub.guoxin.protocol.analysis.utils.ByteUtil;

/**
 * C Signed long long to Java {@link Long}
 * <p>
 * Create by guoxin on 2018/7/9
 */
@Typed(index = 3, numberOfBytes = Long.SIZE / TypeConvert.BIT, description = "{@link Long} C Signed Long Long to Java Long")
public class SignedLongLong2longTypeConvert implements TypeConvert<Long> {

    @Override
    public byte[] encode(Long l) {
        return ByteUtil.getBytes(l);
    }

    @Override
    public Long decode(byte[] bytes) {
        return ByteUtil.getLong(bytes);
    }

}
