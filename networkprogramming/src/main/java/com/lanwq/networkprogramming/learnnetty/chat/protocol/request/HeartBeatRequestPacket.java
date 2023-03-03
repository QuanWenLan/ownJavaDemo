package com.lanwq.networkprogramming.learnnetty.chat.protocol.request;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;

import static com.lanwq.networkprogramming.learnnetty.chat.protocol.command.Command.HEARTBEAT_REQUEST;

/**
 * @program: javaDemo->HeartBeatRequestPacket
 * @description:
 * @author: lanwenquan
 * @date: 2022-12-28 14:24
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
