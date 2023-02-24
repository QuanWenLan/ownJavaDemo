package com.lanwq.networkprogramming.learnnetty.chat.client.console;

import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * @author Vin lan
 * @className ConsoleCommand
 * @description TODO
 * @createTime 2022-12-12  16:58
 **/
public interface ConsoleCommand {
    void exec(Scanner sc, Channel channel);
}
