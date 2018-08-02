package io.github.guoxinl.protocol.analysis.model.entity;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import io.github.guoxinl.protocol.analysis.model.anno.Protocol;

import java.io.Serializable;
import java.util.Objects;

/**
 * 协议：协议头
 * <p>
 * Created by guoxin on 18-2-25.
 */
@Slf4j
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DataProtocolHeader implements ProtocolSerialization {

    private DataProtocolIndexCommand command;
    private int                      version;

    /**
     * 将字节流转换为协议对象
     *
     * @param byteBuf 字节流
     */
    DataProtocolHeader(ByteBuf byteBuf) {
        {
            int commandIndex = byteBuf.readUnsignedByte();
            log.debug("command readerIndex:{}",byteBuf.readerIndex());
            this.command = DataProtocolIndexCommand.create(commandIndex);
        }
        {
            this.version = byteBuf.readUnsignedByte();
            log.debug("version readerIndex:{}",byteBuf.readerIndex());
        }

    }

    DataProtocolHeader(Protocol protocol) {
        this.command = DataProtocolIndexCommand.create(protocol.commandIndex(), protocol.description());
        this.version = protocol.version();
    }

    public String getProtocolKey(){
        return this.getCommand().getIndex() + "-" + this.version;
    }

    /**
     * 将协议对象序列化为字节流
     *
     * @return 字节流
     * @param byteBuf
     */
    @Override
    public void serialization(ByteBuf byteBuf) {
        {
            // 协议 命令
            byteBuf.writeByte(this.command.getIndex());
            log.debug("command writerIndex:{}", byteBuf.writerIndex());
        }
        {
            // 协议 版本
            byteBuf.writeByte(this.version);
            log.debug("version writerIndex:{}", byteBuf.writerIndex());
        }
    }

}
