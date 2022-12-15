package com.lanwq.networkprogramming.learnnetty.chat.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.PacketCodeC;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) {
        PacketCodeC.INSTANCE.encode(out, packet);
    }
}
