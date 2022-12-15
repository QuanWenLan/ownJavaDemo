package com.lanwq.networkprogramming.learnnetty.chat.console.impl;

import com.lanwq.networkprogramming.learnnetty.chat.console.ConsoleCommand;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Vin lan
 * @className CreateGroupConsoleCommand
 * @description
 * @createTime 2022-12-12  17:01
 **/
public class CreateGroupConsoleCommand implements ConsoleCommand {
    private static final String USER_ID_SPLITER = ",";
    @Override
    public void exec(Scanner sc, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
        String userIds = sc.next();
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITER)));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
