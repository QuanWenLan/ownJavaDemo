package com.lanwq.networkprogramming.learnnetty.two;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.Future;

import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * @author Vin lan
 * @className NettyClient
 * @description
 * @createTime 2022-12-05  15:59
 **/
public class NettyClient {
    static int MAX_RETRY = 5;
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });
        // 80 可以连接成功，88则不行，控制台打印连接失败的信息
//        Channel channel = connect(bootstrap, "meituan.com", 80, MAX_RETRY);
        Channel channel = bootstrap.connect("127.0.0.1", 9002).channel();
        while (true) {
            System.out.println("客户端开始写一条数据，" + new Date() + ": hello world!你好！");
            channel.writeAndFlush(new Date() + ": hello world!你好！");
            Thread.sleep(2000);
        }
    }

    /**
     * 默认重连失败，尝试5次
     */
    private static Channel connect(Bootstrap bootstrap, String host, int port, int retry) {
        Channel channel = bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功！");
            } else if (retry == 0) {
                System.out.println("重连次数已用完，放弃连接！");
            } else {
                // 每隔1秒、2秒、4秒、8秒，以2的幂次来建立连接
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + "：连接失败，第" + order + "次重连。。。");
                int retry2 = retry;
                retry2--;
                int finalRetry = retry2;
                bootstrap.config().group().schedule(() -> {
                    connect(bootstrap, host, port, finalRetry);
                }, delay, TimeUnit.SECONDS);
            }
        }).channel();
        return channel;
    }
}
