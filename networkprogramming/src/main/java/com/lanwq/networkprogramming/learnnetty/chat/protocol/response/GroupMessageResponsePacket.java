package com.lanwq.networkprogramming.learnnetty.chat.protocol.response;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;
import com.lanwq.networkprogramming.learnnetty.chat.session.Session;
import lombok.Data;

import static com.lanwq.networkprogramming.learnnetty.chat.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

/**
 * @program: javaDemo->GroupMessageResponsePacket
 * @description:
 * @author: lanwenquan
 * @date: 2022-12-28 10:56
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {

        return GROUP_MESSAGE_RESPONSE;
    }
}
