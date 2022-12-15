package com.lanwq.networkprogramming.learnnetty.chat.protocol.response;

import lombok.Data;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;

import static com.lanwq.networkprogramming.learnnetty.chat.protocol.command.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {
    private String userId;

    private String userName;

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
