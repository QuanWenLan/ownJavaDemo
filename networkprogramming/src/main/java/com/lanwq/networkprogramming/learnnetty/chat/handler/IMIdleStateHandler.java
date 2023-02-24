package com.lanwq.networkprogramming.learnnetty.chat.handler;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @program: javaDemo->IMIdleStateHandler
 * @description: 服务端空闲检测
 * @author: lanwenquan
 * @date: 2022-12-28 14:20
 */
public class IMIdleStateHandler extends IdleStateHandler {

    private static final int READER_IDLE_TIME = 15;

    /**
     * 其中第一个参数是读空闲时间，指的是在这段时间内如果没有读到数据，就表示连接假死；
     * 第二个参数是写空闲时间，指的是在这段时间如果没有写数据，就表示连接假死；
     * 第三个参数是读写空闲时间，指的是在这段时间内如果没有产生数据读或者写，就表示连接假死，写空闲和读写空闲均为0；
     * 最后一个参数是时间单位
     */
    public IMIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        System.out.println(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
        ctx.channel().close();
    }
}
