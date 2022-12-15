package com.lanwq.networkprogramming.learnnetty.channelhandler;

import com.lanwq.networkprogramming.learnnetty.protocol.request.MessageRequestPacket;
import com.lanwq.networkprogramming.learnnetty.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author Vin lan
 * @className MessageRequestHandler
 * @description
 * @createTime 2022-12-12  10:42
 **/
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        System.out.println(new Date() + ": 收到客户端消息: " + msg.getMessage());
        messageResponsePacket.setMessage("服务端回复【" + msg.getMessage() + "】");

        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
