package pub.guoxin.protocol.analysis.conf.convert;

import pub.guoxin.protocol.analysis.utils.ByteUtil;

/**
 * Create by guoxin on 2018/7/9
 */
public class DoubleTypeConvert implements TypeConvert<Double> {

    @Override
    public byte[] encode(Double integer) {
        return ByteUtil.getBytes(integer);
    }

    @Override
    public Double decode(byte[] bytes) {
        return ByteUtil.getDouble(bytes);
    }

    @Override
    public String description() {
        return "{@link Integer} 整形";
    }

    @Override
    public short index() {
        return 4;
    }
}
