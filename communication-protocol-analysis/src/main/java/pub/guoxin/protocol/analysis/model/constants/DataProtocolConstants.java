package pub.guoxin.protocol.analysis.model.constants;

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
         * 命令 2个字节
         */
        int COMMAND_START      = BEGINNING_INDEX;
        int COMMAND_END        = COMMAND_START + 1;
        /**
         * 版本 2个字节
         */
        int VERSION_START      = COMMAND_END;
        int VERSION_END        = VERSION_START + 2;
        /**
         * 数据段数量 2个字节
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
         * 数据码 2个字节
         */
        int CODE_START        = BEGINNING_INDEX;
        int CODE_END          = CODE_START + 2;
        /**
         * 数据类型 1个字节
         */
        int TYPE_START        = CODE_END;
        int TYPE_END          = TYPE_START + 1;
        /**
         * 数据长度 2个字节
         * 数据长度决定 数据段中{data}中能存放的字节数
         */
        int ELEMENT_SIZE_START = TYPE_END;
        int ELEMENT_SIZE_END   = ELEMENT_SIZE_START + 2;

        // totalLength 不固定无法指定
    }

    /**
     * 元素
     */
    interface Element {
        int ELEMENT_LENGTH_START = BEGINNING_INDEX;
        int ELEMENT_LENGTH_END   = ELEMENT_LENGTH_START + 2;

        // totalLength 不固定无法指定
    }
}
