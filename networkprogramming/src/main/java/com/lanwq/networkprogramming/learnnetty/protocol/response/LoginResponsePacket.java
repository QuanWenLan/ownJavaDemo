package com.lanwq.networkprogramming.learnnetty.protocol.response;

import com.lanwq.networkprogramming.learnnetty.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static com.lanwq.networkprogramming.learnnetty.protocol.command.Command.LOGIN_RESPONSE;
/**
 * @author Vin lan
 * @className LoginResponsePacket
 * @description
 * @createTime 2022-12-09  11:11
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
