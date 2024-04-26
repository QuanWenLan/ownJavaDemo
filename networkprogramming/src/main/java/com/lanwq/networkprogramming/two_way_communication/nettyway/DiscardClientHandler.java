package com.lanwq.networkprogramming.two_way_communication.nettyway;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 自定义客户端业务处理类，作用是向服务端写数据
 * @author vin
 */
public class DiscardClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 通道就绪事件，在一个连接建立时被调用。这确保了数据将会被尽可能块地写入服务器
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client：" + ctx);

        //写消息
        ctx.writeAndFlush(Unpooled.copiedBuffer("还钱给我啊，服务器", CharsetUtil.UTF_8));
    }

    /**
     * Netty 里的数据是以 ByteBuf 为单位的，所有需要写出的数据都必须放到一个 ByteBuf 中
     */
    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "还钱给我啊，兄弟，吃土了！".getBytes(CharsetUtil.UTF_8);
        buffer.writeBytes(bytes);
        return buffer;
    }
    /**
     * 通道读取事件，每当接收数据，都会调用这方法。需要注意的是，由服务器发送的消息可能会被分块接收。也就是说
     * 如果服务器发送了5字节，那么不能保证这 5 字节会被一次性接收
     */
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
