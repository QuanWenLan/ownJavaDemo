package com.lanwq.networkprogramming.learnnetty.chat.protocol.request;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;
import lombok.Data;

import static com.lanwq.networkprogramming.learnnetty.chat.protocol.command.Command.QUIT_GROUP_REQUEST;

/**
 * @program: javaDemo->QuitGroupRequestPacket
 * @description:
 * @author: lanwenquan
 * @date: 2022-12-27 16:34
 */
@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_REQUEST;
    }
}
