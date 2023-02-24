package com.lanwq.networkprogramming.learnnetty.chat.protocol.request;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.lanwq.networkprogramming.learnnetty.chat.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;

/**
 * @program: javaDemo->ListGroupMembersRequestPacket
 * @description:
 * @author: lanwenquan
 * @date: 2022-12-27 16:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
