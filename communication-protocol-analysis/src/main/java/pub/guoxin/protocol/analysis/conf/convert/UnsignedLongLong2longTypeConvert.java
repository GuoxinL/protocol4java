//package pub.guoxin.protocol.analysis.conf.convert;
//
//import io.netty.buffer.ByteBuf;
//import pub.guoxin.protocol.analysis.model.anno.Typed;
//import pub.guoxin.protocol.analysis.utils.ByteUtil;
//
//import java.math.BigInteger;
//
///**
// * TODO this using {@link BigInteger} implementation
// * C Unsigned long long to Java {@link Long}
// * <p>
// * Create by guoxin on 2018/7/9
// */
//@Typed(index = 13, numberOfBytes = Long.SIZE / TypeConvert.BIT, description = "{@link Long} C Unsigned Long Long to Java Long")
//public class UnsignedLongLong2longTypeConvert implements TypeConvert<BigInteger> {
//
//    /**
//     * TODO Unsigned
//     */
//    @Override
//    public ByteBuf encode(BigInteger l) {
//        return null;
//    }
//
//    @Override
//    public BigInteger decode(ByteBuf bytes) {
//        return null;
//    }
//
//}
