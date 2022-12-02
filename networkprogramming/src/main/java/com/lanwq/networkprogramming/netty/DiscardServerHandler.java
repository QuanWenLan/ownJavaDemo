package com.lanwq.networkprogramming.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @ClassName DiscardServerHandler
 * @Description 该组件实现了服务器对从客户端接受的数据的处理
 *  * 服务器需要响应传入的消息，所以需要实现 ChannelInboundHandler 接口
 * @Author Administrator
 * @Date 2019/12/17 14:55
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter  {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // DiscardServerHandler the received data silently.
//        ((ByteBuf) msg).release(); // (3)

        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) { // (1)
                System.out.print((char) in.readByte());
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }
    }

    /**
     * 通知 channelInboundHandler 最后一次对 channelRead 的调用是当前批量读取中的最后一条信息
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将未决消息冲刷到远程节点，并且关闭该 channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
