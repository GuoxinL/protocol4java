package pub.guoxin.protocol.samples;

import pub.guoxin.protocol.analysis.conf.convert.SignedInt2integerTypeConvert;
import pub.guoxin.protocol.analysis.conf.convert.SignedShort2shortTypeConvert;
import pub.guoxin.protocol.analysis.conf.convert.StringTypeConvert;
import pub.guoxin.protocol.analysis.model.DataProtocolCallbackService;
import pub.guoxin.protocol.analysis.model.anno.Callback;
import pub.guoxin.protocol.analysis.model.anno.CodeIndex;
import pub.guoxin.protocol.analysis.model.anno.Protocol;
import pub.guoxin.protocol.analysis.model.anno.TypeIndex;
import pub.guoxin.protocol.analysis.model.entity.ProtocolEntity;

/**
 * Create by guoxin on 2018/6/13
 */
@Protocol(commandIndex = 1, version = 1)
@Callback(callback = DataProtocolCallbackService.class)
public class UpgradeProtocol implements ProtocolEntity {

    @TypeIndex(convert = StringTypeConvert.class)
    @CodeIndex(index = 0, description = "aaaaa")
    private String   aaa;

    @TypeIndex(convert = StringTypeConvert.class)
    @CodeIndex(index = 1, description = "bbbbb")
    private String   bbb;

    @CodeIndex(index = 2, description = "ccccc")
    @TypeIndex(convert = SignedInt2integerTypeConvert.class)
    private int[]    ccc;

    @CodeIndex(index = 3, description = "ddddd")
    @TypeIndex(convert = StringTypeConvert.class)
    private String[] ddd;

    @CodeIndex(index = 4, description = "eeeee")
    @TypeIndex(convert = SignedShort2shortTypeConvert.class)
    private short[]  eee;
}
