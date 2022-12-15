package com.lanwq.networkprogramming.learnnetty.chat.protocol.response;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;
import lombok.Data;


import static com.lanwq.networkprogramming.learnnetty.chat.protocol.command.Command.LOGOUT_RESPONSE;

@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGOUT_RESPONSE;
    }
}
