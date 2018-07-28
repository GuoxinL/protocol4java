package io.github.protocol.analysis.model.entity;

import io.netty.buffer.ByteBuf;

/**
 * 协议序列化
 *
 * Create by guoxin on 2018/7/8
 */
public interface ProtocolSerialization {
    void serialization(ByteBuf byteBuf);
}
