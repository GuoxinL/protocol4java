package pub.guoxin.protocol.analysis.conf.convert;

import pub.guoxin.protocol.analysis.utils.ByteUtil;

/**
 * Create by guoxin on 2018/7/9
 */
public class BooleanTypeConvert implements TypeConvert<Boolean> {

    @Override
    public byte[] encode(Boolean integer) {
        return ByteUtil.getBytes(integer);
    }

    @Override
    public Boolean decode(byte[] bytes) {
        return ByteUtil.getBoolean(bytes);
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
