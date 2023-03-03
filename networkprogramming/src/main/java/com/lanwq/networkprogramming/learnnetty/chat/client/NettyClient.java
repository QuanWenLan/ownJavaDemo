package com.lanwq.networkprogramming.learnnetty.chat.client;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.lanwq.networkprogramming.learnnetty.chat.client.console.ConsoleCommandManager;
import com.lanwq.networkprogramming.learnnetty.chat.client.console.impl.LoginConsoleCommand;
import com.lanwq.networkprogramming.learnnetty.chat.client.handler.CreateGroupResponseHandler;
import com.lanwq.networkprogramming.learnnetty.chat.client.handler.GroupMessageResponseHandler;
import com.lanwq.networkprogramming.learnnetty.chat.client.handler.JoinGroupResponseHandler;
import com.lanwq.networkprogramming.learnnetty.chat.client.handler.ListGroupMembersResponseHandler;
import com.lanwq.networkprogramming.learnnetty.chat.client.handler.LoginResponseHandler;
import com.lanwq.networkprogramming.learnnetty.chat.client.handler.LogoutResponseHandler;
import com.lanwq.networkprogramming.learnnetty.chat.client.handler.MessageResponseHandler;
import com.lanwq.networkprogramming.learnnetty.chat.client.handler.QuitGroupResponseHandler;
import com.lanwq.networkprogramming.learnnetty.chat.codec.PacketDecoder;
import com.lanwq.networkprogramming.learnnetty.chat.codec.PacketEncoder;
import com.lanwq.networkprogramming.learnnetty.chat.codec.Spliter;
import com.lanwq.networkprogramming.learnnetty.chat.handler.IMIdleStateHandler;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.request.LoginRequestPacket;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.request.MessageRequestPacket;
import com.lanwq.networkprogramming.learnnetty.chat.server.handler.HeartBeatTimerHandler;
import com.lanwq.networkprogramming.learnnetty.chat.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author 闪电侠
 * 1.客户端启动之后，我们在控制台输入用户名，服务端随机分配一个userId给客户端，这里我们省去了通过账号、密码注册的过程，userId就在服务端随机生成了，
 * 生产环境中可能会持久化在数据库，然后每次通过账号、密码去“捞”。
 * 2.当有两个客户端登录成功之后，在控制台输入userId+空格+消息，这里的userId是消息接收方的标识，消息接收方的控制台接着就会显示另外一个客户端发来的消息。
 */
public class NettyClient {
    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;


    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        // 空闲检测
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        // 登录响应处理器
                        ch.pipeline().addLast(new LoginResponseHandler());
                        // 收消息处理器
                        ch.pipeline().addLast(new MessageResponseHandler());
                        // 创建群响应处理器
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        // 加群响应处理器
                        ch.pipeline().addLast(new JoinGroupResponseHandler());
                        // 退群响应处理器
                        ch.pipeline().addLast(new QuitGroupResponseHandler());
                        // 获取群成员响应处理器
                        ch.pipeline().addLast(new ListGroupMembersResponseHandler());
                        // 群消息响应
                        ch.pipeline().addLast(new GroupMessageResponseHandler());
                        // 登出响应处理器
                        ch.pipeline().addLast(new LogoutResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                        // 心跳定时器
                        ch.pipeline().addLast(new HeartBeatTimerHandler());
                    }
                });

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 连接成功，启动控制台线程……");
                Channel channel = ((ChannelFuture) future).channel();
//                startConsoleThread(channel);
                startConsoleThread2(channel);
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    System.out.print("输入用户名登录: ");
                    String username = sc.nextLine();
                    loginRequestPacket.setUserName(username);

                    // 密码使用默认的
                    loginRequestPacket.setPassword("pwd");

                    // 发送登录数据包
                    channel.writeAndFlush(loginRequestPacket);
                    waitForLoginResponse();
                } else {
                    String toUserId = sc.next();
                    String message = sc.next();
                    channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
                }
            }
        }).start();
    }


    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * 群聊
     */
    private static void startConsoleThread2(Channel channel) {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                } else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }
}
