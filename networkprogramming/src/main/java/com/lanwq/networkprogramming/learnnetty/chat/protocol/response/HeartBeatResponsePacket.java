package com.lanwq.networkprogramming.learnnetty.chat.protocol.response;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;

import static com.lanwq.networkprogramming.learnnetty.chat.protocol.command.Command.HEARTBEAT_RESPONSE;

/**
 * @program: javaDemo->HeartBeatResponsePacket
 * @description:
 * @author: lanwenquan
 * @date: 2022-12-28 14:25
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
