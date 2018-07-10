package pub.guoxin.protocol.analysis.conf.convert;

import pub.guoxin.protocol.analysis.utils.ByteUtil;

/**
 * Create by guoxin on 2018/7/9
 */
public class LongTypeConvert implements TypeConvert<Long> {

    @Override
    public byte[] encode(Long integer) {
        return ByteUtil.getBytes(integer);
    }

    @Override
    public Long decode(byte[] bytes) {
        return ByteUtil.getLong(bytes);
    }

    @Override
    public String description() {
        return "{@link Long} 长整型";
    }

    @Override
    public short index() {
        return 2;
    }
}
