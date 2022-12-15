package com.lanwq.networkprogramming.learnnetty.chat.server;

import com.lanwq.networkprogramming.learnnetty.chat.server.handler.CreateGroupRequestHandler;
import com.lanwq.networkprogramming.learnnetty.chat.server.handler.LogoutRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import com.lanwq.networkprogramming.learnnetty.chat.codec.PacketDecoder;
import com.lanwq.networkprogramming.learnnetty.chat.codec.PacketEncoder;
import com.lanwq.networkprogramming.learnnetty.chat.codec.Spliter;
import com.lanwq.networkprogramming.learnnetty.chat.server.handler.AuthHandler;
import com.lanwq.networkprogramming.learnnetty.chat.server.handler.LoginRequestHandler;
import com.lanwq.networkprogramming.learnnetty.chat.server.handler.MessageRequestHandler;

import java.util.Date;

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
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new AuthHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());
                        // == 群聊
                        ch.pipeline().addLast(new CreateGroupRequestHandler());
                        // ==
                        ch.pipeline().addLast(new LogoutRequestHandler());
                        ch.pipeline().addLast(new PacketEncoder());
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
