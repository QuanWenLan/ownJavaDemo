package com.lanwq.networkprogramming.learnnetty.chat.protocol.request;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.lanwq.networkprogramming.learnnetty.chat.protocol.command.Command.GROUP_MESSAGE_REQUEST;

/**
 * @program: javaDemo->GroupMessageRequestPacket
 * @description:
 * @author: lanwenquan
 * @date: 2022-12-28 10:55
 */
@Data
@NoArgsConstructor
public class GroupMessageRequestPacket extends Packet {
    private String toGroupId;
    private String message;

    public GroupMessageRequestPacket(String toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}
