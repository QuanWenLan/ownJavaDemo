package com.lanwq.networkprogramming.learnnetty.chat.protocol.response;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.lanwq.networkprogramming.learnnetty.chat.protocol.command.Command.JOIN_GROUP_RESPONSE;

/**
 * @program: javaDemo->JoinGroupResponsePacket
 * @description:
 * @author: lanwenquan
 * @date: 2022-12-27 16:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JoinGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private String reason;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPONSE;
    }
}
