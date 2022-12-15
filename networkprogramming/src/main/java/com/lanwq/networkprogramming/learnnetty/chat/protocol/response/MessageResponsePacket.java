package com.lanwq.networkprogramming.learnnetty.chat.protocol.response;

import lombok.Data;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;

import static com.lanwq.networkprogramming.learnnetty.chat.protocol.command.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}
