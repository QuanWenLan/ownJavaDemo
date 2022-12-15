package com.lanwq.networkprogramming.learnnetty.channelhandler;

import com.lanwq.networkprogramming.learnnetty.protocol.request.LoginRequestPacket;
import com.lanwq.networkprogramming.learnnetty.protocol.response.LoginResponsePacket;
import com.lanwq.networkprogramming.learnnetty.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @author Vin lan
 * @className LoginResponseHandler
 * @description
 * @createTime 2022-12-12  12:19
 **/
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() +"：客户端开始登录！");

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("lan");
        loginRequestPacket.setPassword("pwd");

        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println(new Date() + "：客户端登陆成功");
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.out.println(new Date() + ": 客户端登录失败，原因：" + msg.getReason());
        }
    }

}
