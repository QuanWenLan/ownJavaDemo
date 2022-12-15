package com.lanwq.networkprogramming.learnnetty.chat.protocol.request;

import lombok.Data;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;

import static com.lanwq.networkprogramming.learnnetty.chat.protocol.command.Command.LOGIN_REQUEST;

@Data
public class LoginRequestPacket extends Packet {
    private String userName;

    private String password;

    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }
}
