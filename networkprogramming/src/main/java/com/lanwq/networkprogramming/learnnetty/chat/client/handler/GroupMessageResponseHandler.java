package com.lanwq.networkprogramming.learnnetty.chat.client.handler;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.response.GroupMessageResponsePacket;
import com.lanwq.networkprogramming.learnnetty.chat.session.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: javaDemo->GroupMessageResponseHandler
 * @description:
 * @author: lanwenquan
 * @date: 2022-12-28 10:57
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket responsePacket) {
        String fromGroupId = responsePacket.getFromGroupId();
        Session fromUser = responsePacket.getFromUser();
        System.out.println("收到群[" + fromGroupId + "]中[" + fromUser + "]发来的消息：" + responsePacket.getMessage());
    }
}
