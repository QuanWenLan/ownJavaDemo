package com.lanwq.networkprogramming.learnnetty.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.response.LogoutResponsePacket;
import com.lanwq.networkprogramming.learnnetty.chat.util.SessionUtil;

public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
