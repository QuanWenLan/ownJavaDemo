package com.lanwq.networkprogramming.learnnetty.chat.server.handler;

import java.util.Date;
import java.util.UUID;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.request.LoginRequestPacket;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.response.LoginResponsePacket;
import com.lanwq.networkprogramming.learnnetty.chat.session.Session;
import com.lanwq.networkprogramming.learnnetty.chat.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lanwq
 * 非常重要的是，如果一个Handler要被多个Channel共享，必须加上@ChannelHandler.Sharable显式地告诉Netty，
 * 这个Handler是支持多个Channel共享的，否则会报错.
 * 当然，如果你的Handler里有与Channel相关的成员变量，那么就不要写成单例模式。不过，所有的状态其实都可以绑定在Channel的属性上，依然可以改造成单例模式
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());

        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            String userId = randomUserId();
            loginResponsePacket.setUserId(userId);
            System.out.println("[" + loginRequestPacket.getUserName() + "]登录成功");
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUserName()), ctx.channel());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
