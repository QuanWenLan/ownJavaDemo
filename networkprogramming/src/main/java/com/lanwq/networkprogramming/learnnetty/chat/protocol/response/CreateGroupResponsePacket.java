package com.lanwq.networkprogramming.learnnetty.chat.protocol.response;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.lanwq.networkprogramming.learnnetty.chat.protocol.command.Command.CREATE_GROUP_RESPONSE;

@Data
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_RESPONSE;
    }
}
