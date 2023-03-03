package com.lanwq.networkprogramming.learnnetty.chat.server.handler;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.request.JoinGroupRequestPacket;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.response.JoinGroupResponsePacket;
import com.lanwq.networkprogramming.learnnetty.chat.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @program: javaDemo->JoinGroupRequestHandler
 * @description:
 * @author: lanwenquan
 * @date: 2022-12-27 16:08
 */
@ChannelHandler.Sharable
public class JoinGroupRequestHandler  extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    private JoinGroupRequestHandler() {

    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        // 1 获取群对应的 ChannelGroup，然后将当前用户的 Channel 加进去
        String groupId = joinGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        // 2 构造加群响应发送给客户端
        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);

        ctx.channel().writeAndFlush(responsePacket);
    }
}
