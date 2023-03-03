package com.lanwq.networkprogramming.learnnetty.chat.client.console;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.lanwq.networkprogramming.learnnetty.chat.client.console.impl.CreateGroupConsoleCommand;
import com.lanwq.networkprogramming.learnnetty.chat.client.console.impl.JoinGroupConsoleCommand;
import com.lanwq.networkprogramming.learnnetty.chat.client.console.impl.ListGroupMembersConsoleCommand;
import com.lanwq.networkprogramming.learnnetty.chat.client.console.impl.LogoutConsoleCommand;
import com.lanwq.networkprogramming.learnnetty.chat.client.console.impl.QuitGroupConsoleCommand;
import com.lanwq.networkprogramming.learnnetty.chat.client.console.impl.SendToGroupConsoleCommand;
import com.lanwq.networkprogramming.learnnetty.chat.client.console.impl.SendToUserConsoleCommand;
import io.netty.channel.Channel;

/**
 * @author Vin lan
 * @className ConsoleCommandManager
 * @description
 * @createTime 2022-12-12  16:58
 **/
public class ConsoleCommandManager implements ConsoleCommand {
    private Map<String, ConsoleCommand> consoleCommandMap;
    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
        consoleCommandMap.put("quitGroup", new QuitGroupConsoleCommand());
        consoleCommandMap.put("listGroupMembers", new ListGroupMembersConsoleCommand());
        consoleCommandMap.put("sendToGroup", new SendToGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner sc, Channel channel) {
        String command = sc.next();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if (consoleCommand != null) {
            consoleCommand.exec(sc, channel);
        } else {
            System.out.println("无法识别【" + command + "】指令，请重新输入！");
        }
    }
}
