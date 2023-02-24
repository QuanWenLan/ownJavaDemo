package com.lanwq.networkprogramming.learnnetty.chat.client.console.impl;

import java.util.Scanner;

import com.lanwq.networkprogramming.learnnetty.chat.client.console.ConsoleCommand;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

/**
 * @program: javaDemo->JoinGroupConsoleCommand
 * @description: 加入群组命令
 * @author: lanwenquan
 * @date: 2022-12-27 16:03
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

        System.out.println("输入 group id，加入群聊：");
        final String groupId = sc.next();
        joinGroupRequestPacket.setGroupId(groupId);

        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
