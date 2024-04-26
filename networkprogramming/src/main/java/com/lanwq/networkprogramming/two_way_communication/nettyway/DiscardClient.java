package com.lanwq.networkprogramming.two_way_communication.nettyway;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName NettyClient
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/19 9:29
 */
public class DiscardClient {
    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // (1) 客户端启动引导类
            Bootstrap b = new Bootstrap();
            // (2) 事件处理
            b.group(workerGroup);
            // (3) 使用 nio 连接
            b.channel(NioSocketChannel.class);
            // (4)
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new DiscardClientHandler());

                }
            });

            // Start the client.
            ChannelFuture f = b.connect("127.0.0.1", 7777).sync(); // (5)

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }
}
