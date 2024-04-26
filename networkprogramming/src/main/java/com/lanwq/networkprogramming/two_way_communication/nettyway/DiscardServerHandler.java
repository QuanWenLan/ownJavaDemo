package com.lanwq.networkprogramming.two_way_communication.nettyway;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;

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
        System.out.println(new Date() + "：服务端读到数据-》"+ in.toString(CharsetUtil.UTF_8));
        /*try {
            while (in.isReadable()) {
                System.out.print((char) in.readByte());
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }*/

        System.out.println(new Date() + "：服务端写回数据-》" + "我还给你（客户端），没钱也还！");
        ByteBuf buf = getByteBuf(ctx, "我还给你（客户端），没钱也还！");
        ctx.channel().writeAndFlush(buf);
        // 使用完记得要释放 ByteBuf，因为它使用的是堆外内存，不受JVM管理
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx, String msg) {
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = msg.getBytes(CharsetUtil.UTF_8);
        buffer.writeBytes(bytes);
        return buffer;
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
