package com.lanwq.networkprogramming.learnnetty.channelhandler.auth;

import com.lanwq.networkprogramming.learnnetty.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Vin lan
 * @className AuthHandler
 * @description
 * @createTime 2022-12-12  15:34
 **/
public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!LoginUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            // 一行代码删除
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if(LoginUtil.hasLogin(ctx.channel())) {
            System.out.println("当前连接登陆验证完毕，无须再次验证，AuthHandler 被移除");
        } else {
            System.out.println("无登录验证，强制关闭连接！");
        }
    }
}
