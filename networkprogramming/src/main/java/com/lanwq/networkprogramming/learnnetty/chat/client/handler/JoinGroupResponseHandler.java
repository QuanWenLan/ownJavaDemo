package com.lanwq.networkprogramming.learnnetty.chat.client.handler;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: javaDemo->JoinGroupResponseHandler
 * @description:
 * @author: lanwenquan
 * @date: 2022-12-27 16:08
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            System.out.println("加入群聊【"+responsePacket.getGroupId()+"】成功！");
        } else {
            System.out.println("加入群聊【"+responsePacket.getGroupId()+"】失败，原因为：" + responsePacket.getReason());
        }
    }
}
