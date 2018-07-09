package pub.guoxin.protocol.analysis.conf.convert;

import pub.guoxin.protocol.analysis.utils.ByteUtil;

/**
 * Create by guoxin on 2018/7/9
 */
public class IntegerTypeConvert implements TypeConvert<Integer> {

    @Override
    public byte[] decode(Integer integer) {
        return ByteUtil.getBytes(integer);
    }

    @Override
    public Integer encode(byte[] bytes) {
        return ByteUtil.getInt(bytes);
    }

    @Override
    public String description() {
        return "{@link Integer} 整形";
    }

    @Override
    public short index() {
        return 3;
    }
}
