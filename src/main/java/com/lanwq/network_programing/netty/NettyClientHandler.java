package com.lanwq.network_programing.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Create By zdw on 2019/7/23
 * 自定义客户端业务处理类
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    //通道就绪事件
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client：" + ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("还钱给我啊", CharsetUtil.UTF_8));//写消息
    }

    //通道读取事件
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务端发送来的数据：" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    //通道读取完毕事件
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    //异常发生事件
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}