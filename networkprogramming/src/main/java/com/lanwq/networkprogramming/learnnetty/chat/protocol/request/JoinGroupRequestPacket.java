package com.lanwq.networkprogramming.learnnetty.chat.protocol.request;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.lanwq.networkprogramming.learnnetty.chat.protocol.command.Command.JOIN_GROUP_REQUEST;

/**
 * @program: javaDemo->JoinGroupRequestPacket
 * @description: 加入群组访问包
 * @author: lanwenquan
 * @date: 2022-12-27 16:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JoinGroupRequestPacket extends Packet {
    private String groupId;
    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }
}
