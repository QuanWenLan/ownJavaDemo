package com.lanwq.networkprogramming.learnnetty.channelhandler;

import com.lanwq.networkprogramming.learnnetty.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author Vin lan
 * @className MessageResponseHandler
 * @description
 * @createTime 2022-12-12  12:19
 **/
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        System.out.println(new Date() + ": 收到服务端的消息: " + msg.getMessage());
    }
}
