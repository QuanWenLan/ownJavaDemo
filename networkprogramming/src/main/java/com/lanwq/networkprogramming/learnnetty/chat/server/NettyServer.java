package com.lanwq.networkprogramming.learnnetty.chat.server;

import java.util.Date;

import com.lanwq.networkprogramming.learnnetty.chat.codec.PacketCodecHandler;
import com.lanwq.networkprogramming.learnnetty.chat.codec.Spliter;
import com.lanwq.networkprogramming.learnnetty.chat.handler.IMIdleStateHandler;
import com.lanwq.networkprogramming.learnnetty.chat.server.handler.AuthHandler;
import com.lanwq.networkprogramming.learnnetty.chat.server.handler.HeartBeatRequestHandler;
import com.lanwq.networkprogramming.learnnetty.chat.server.handler.IMHandler;
import com.lanwq.networkprogramming.learnnetty.chat.server.handler.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyServer {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        // 空闲检测，放在最前面是因为假如插到最后面，如果这个连接读到了数据，但是在inbound传播的过程中出错了或者数据处理完毕就不往后传递了
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        /*
                        对于Pipeline中的第一个Handler—Spliter，我们是无法改动它的，
                        因为它的内部实现与每个Channel都有关，每个Spliter都需要维持每个Channel当前读到的数据，
                        也就是说它是有状态的。
                         */
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
//                        ch.pipeline().addLast(new PacketDecoder());
                        // 登录请求处理器
                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                        ch.pipeline().addLast(AuthHandler.INSTANCE);
                        // == 开始
                        // 因为解码器decode出来的每个Packet对象都要在每个Handler上经过一遍，所以优化成 IMHandler 类的操作
                        /*// 单聊消息请求处理器
                        ch.pipeline().addLast(MessageRequestHandler.INSTANCE);
                        // 创建群请求处理器
                        ch.pipeline().addLast(CreateGroupRequestHandler.INSTANCE);
                        // 加群请求处理器
                        ch.pipeline().addLast(JoinGroupRequestHandler.INSTANCE);
                        // 退群请求处理器
                        ch.pipeline().addLast(QuitGroupRequestHandler.INSTANCE);
                        // 获取群成员请求处理器
                        ch.pipeline().addLast(ListGroupMembersRequestHandler.INSTANCE);
                        // 群消息响应
                        ch.pipeline().addLast(GroupMessageRequestHandler.INSTANCE);
                        // 登出请求处理器
                        ch.pipeline().addLast(LogoutRequestHandler.INSTANCE);*/
//                        ch.pipeline().addLast(new PacketEncoder());
                        // == 结束，等价于下面的代码
                        ch.pipeline().addLast(IMHandler.INSTANCE);

                    }
                });


        bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}
