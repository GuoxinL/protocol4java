package pub.guoxin.protocol.model.constants;

/**
 * 通信远传数据协议
 * <p>
 * Created by guoxin on 18-2-10.
 */
public interface DataProtocolConstants {
    /**
     * 初始下标
     */
    int BEGINNING_INDEX = 0;

    /**
     * 协议头
     */
    interface Header {
        /**
         * 命令 两个字节
         */
        int COMMAND_START      = BEGINNING_INDEX;
        int COMMAND_END        = COMMAND_START + 2;
        /**
         * 版本 两个字节
         */
        int VERSION_START      = COMMAND_END;
        int VERSION_END        = VERSION_START + 2;
        /**
         * 总长度 两个字节
         */
        int TOTAL_PACKET_START = VERSION_END;
        int TOTAL_PACKET_END   = TOTAL_PACKET_START + 2;
        /**
         * 协议头 总长度
         */
        int TOTAL_LENGTH       = COMMAND_END;
    }

    /**
     * 数据段
     */
    interface Packet {
        /**
         * 数据码 两个字节
         */
        int CODE_START        = BEGINNING_INDEX;
        int CODE_END          = CODE_START + 2;
        /**
         * 数据类型 一个字节
         */
        int TYPE_START        = CODE_END;
        int TYPE_END          = TYPE_START + 1;
        /**
         * 数据长度 1个字节
         * 数据长度决定 数据段中{data}中能存放的字节数
         */
        int DATA_LENGTH_START = TYPE_END;
        int DATA_LENGTH_END   = DATA_LENGTH_START + 1;

    }

}
