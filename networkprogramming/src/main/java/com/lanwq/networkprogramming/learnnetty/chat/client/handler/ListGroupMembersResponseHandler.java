package com.lanwq.networkprogramming.learnnetty.chat.client.handler;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.response.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: javaDemo->ListGroupMembersResponseHandler
 * @description:
 * @author: lanwenquan
 * @date: 2022-12-27 16:39
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket responsePacket) {
        System.out.println("群[" + responsePacket.getGroupId() + "]中的人包括：" + responsePacket.getSessionList());
    }
}
