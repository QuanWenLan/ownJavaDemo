package com.lanwq.networkprogramming.learnnetty.chat.console.impl;

import com.lanwq.networkprogramming.learnnetty.chat.console.ConsoleCommand;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author Vin lan
 * @className LogoutConsoleCommand
 * @description
 * @createTime 2022-12-12  17:01
 **/
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
