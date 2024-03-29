package com.lanwq.networkprogramming.learnnetty.chat.client.console.impl;

import java.util.Scanner;

import com.lanwq.networkprogramming.learnnetty.chat.client.console.ConsoleCommand;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

/**
 * @program: javaDemo->QuitGroupConsoleCommand
 * @description:
 * @author: lanwenquan
 * @date: 2022-12-27 17:20
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();

        System.out.print("输入 groupId，退出群聊：");
        String groupId = scanner.next();

        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
