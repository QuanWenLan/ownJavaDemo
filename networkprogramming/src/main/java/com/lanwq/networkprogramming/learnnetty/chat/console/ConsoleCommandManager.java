package com.lanwq.networkprogramming.learnnetty.chat.console;

import com.lanwq.networkprogramming.learnnetty.chat.console.ConsoleCommand;
import com.lanwq.networkprogramming.learnnetty.chat.console.impl.CreateGroupConsoleCommand;
import com.lanwq.networkprogramming.learnnetty.chat.console.impl.LogoutConsoleCommand;
import com.lanwq.networkprogramming.learnnetty.chat.console.impl.SendToUserConsoleCommand;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
        consoleCommandMap.put("sentToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
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
