package pub.guoxin.protocol.analysis.conf.convert;

import pub.guoxin.protocol.analysis.utils.ByteUtil;

/**
 * Create by guoxin on 2018/7/9
 */
public class ShortTypeConvert implements TypeConvert<Short> {

    @Override
    public byte[] encode(Short integer) {
        return ByteUtil.getBytes(integer);
    }

    @Override
    public Short decode(byte[] bytes) {
        return ByteUtil.getShort(bytes);
    }

    @Override
    public String description() {
        return "{@link Integer} 短整形";
    }

    @Override
    public short index() {
        return 0;
    }
}
