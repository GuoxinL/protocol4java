package pub.guoxin.protocol.analysis.conf.convert;

import pub.guoxin.protocol.analysis.utils.ByteUtil;

/**
 * Create by guoxin on 2018/7/9
 */
public class FloatTypeConvert implements TypeConvert<Float> {

    @Override
    public byte[] decode(Float integer) {
        return ByteUtil.getBytes(integer);
    }

    @Override
    public Float encode(byte[] bytes) {
        return ByteUtil.getFloat(bytes);
    }

    @Override
    public String description() {
        return "{@link Integer} 整形";
    }

    @Override
    public short index() {
        return 5;
    }
}
