package com.lanwq.networkprogramming.learnnetty.chat.protocol.response;

/**
 * @program: javaDemo->QuitGroupResponsePacket
 * @description:
 * @author: lanwenquan
 * @date: 2022-12-27 16:34
 */

import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.lanwq.networkprogramming.learnnetty.chat.protocol.command.Command.QUIT_GROUP_RESPONSE;


@EqualsAndHashCode(callSuper = true)
@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_RESPONSE;
    }
}