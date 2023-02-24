package com.lanwq.networkprogramming.learnnetty.chat.protocol.response;

import java.util.List;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;
import com.lanwq.networkprogramming.learnnetty.chat.session.Session;
import lombok.Data;

import static com.lanwq.networkprogramming.learnnetty.chat.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

/**
 * @program: javaDemo->ListGroupMembersResponsePacket
 * @description:
 * @author: lanwenquan
 * @date: 2022-12-27 16:36
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}