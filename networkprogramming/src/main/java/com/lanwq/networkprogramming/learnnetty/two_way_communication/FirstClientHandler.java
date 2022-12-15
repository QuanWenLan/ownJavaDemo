package com.lanwq.networkprogramming.learnnetty.two_way_communication;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author vin
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ": 客户端写出数据");
        /*
         * 这种情况发送多次，会出现数据
         * （1）字符串被拆开的情况，形成一个破碎的包（半包）：（服务端读到数据 -> �，闪电侠!你好，闪电侠!你好）
         * （2）正常字符串的情况：（服务端读到数据 -> 你好，闪电侠!）
         * （3）多个字符串粘在了一起：（服务端读到数据 -> 你好，闪电侠!你好，闪电侠!你好，闪电侠!）
         */
        for (int i = 0; i < 1000; i++) {
            // 1.获取数据
            ByteBuf buffer = getByteBuf(ctx);

            // 2.写数据
            ctx.channel().writeAndFlush(buffer);
        }
        /*// 1.获取数据
        ByteBuf buffer = getByteBuf(ctx);

        // 2.写数据
        ctx.channel().writeAndFlush(buffer);*/
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "你好，闪电侠!".getBytes(Charset.forName("utf-8"));

        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes(bytes);

        return buffer;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + ": 客户端读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));
    }
}
