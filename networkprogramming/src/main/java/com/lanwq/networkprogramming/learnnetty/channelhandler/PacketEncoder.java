package com.lanwq.networkprogramming.learnnetty.channelhandler;

import com.lanwq.networkprogramming.learnnetty.protocol.Packet;
import com.lanwq.networkprogramming.learnnetty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Vin lan
 * @className PacketEncoder
 * @description
 * @createTime 2022-12-12  10:44
 **/
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodeC.INSTANCE.encode(out, msg);
    }
}
