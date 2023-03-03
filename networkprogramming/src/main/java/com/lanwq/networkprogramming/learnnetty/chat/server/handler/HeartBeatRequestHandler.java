package com.lanwq.networkprogramming.learnnetty.chat.server.handler;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.request.HeartBeatRequestPacket;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: javaDemo->HeartBeatRequestHandler
 * @description:
 * @author: lanwenquan
 * @date: 2022-12-28 14:26
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {
    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    private HeartBeatRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket requestPacket) {
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
