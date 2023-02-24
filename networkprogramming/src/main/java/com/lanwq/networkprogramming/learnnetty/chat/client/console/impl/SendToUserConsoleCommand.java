package com.lanwq.networkprogramming.learnnetty.chat.client.console.impl;

import java.util.Scanner;

import com.lanwq.networkprogramming.learnnetty.chat.client.console.ConsoleCommand;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.request.MessageRequestPacket;
import io.netty.channel.Channel;

/**
 * @author Vin lan
 * @className SendTouserConsoleCommand
 * @description
 * @createTime 2022-12-12  17:01
 **/
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.print("发送消息给某个某个用户：");

        String toUserId = sc.next();
        String message = sc.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }
}
