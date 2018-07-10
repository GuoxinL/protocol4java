package pub.guoxin.protocol.analysis.conf.convert;

import pub.guoxin.protocol.analysis.utils.ByteUtil;

/**
 * Create by guoxin on 2018/7/9
 */
public class StringTypeConvert implements TypeConvert<String> {

    @Override
    public byte[] encode(String s) {
        return ByteUtil.getBytes(s);
    }

    @Override
    public String decode(byte[] bytes) {
        return ByteUtil.getString(bytes);
    }

    @Override
    public String description() {
        return "{@link String} 字符串";
    }

    @Override
    public short index() {
        return 6;
    }
}
