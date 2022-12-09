package com.lanwq.networkprogramming.learnnetty.protocol.response;

import com.lanwq.networkprogramming.learnnetty.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static com.lanwq.networkprogramming.learnnetty.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author Vin lan
 * @className MessageRequestPacket
 * @description
 * @createTime 2022-12-09  11:57
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponsePacket extends Packet {

    private String message;
    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
