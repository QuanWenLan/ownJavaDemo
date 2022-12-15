package com.lanwq.networkprogramming.learnnetty.chat.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.request.LogoutRequestPacket;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.response.LogoutResponsePacket;
import com.lanwq.networkprogramming.learnnetty.chat.util.SessionUtil;

/**
 * 登出请求
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) {
        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
