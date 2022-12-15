package com.lanwq.networkprogramming.learnnetty.chat.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author Vin lan
 * @className ConsoleCommand
 * @description TODO
 * @createTime 2022-12-12  16:58
 **/
public interface ConsoleCommand {
    void exec(Scanner sc, Channel channel);
}
