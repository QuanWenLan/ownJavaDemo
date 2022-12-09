package com.lanwq.networkprogramming.learnnetty.protocol.request;

import com.lanwq.networkprogramming.learnnetty.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static com.lanwq.networkprogramming.learnnetty.protocol.command.Command.LOGIN_REQUEST;

/**
 * @author Vin lan
 * @className LoginRequestPacket
 * @description
 * @createTime 2022-12-08  17:12
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestPacket extends Packet {
    private String userId;
    private String userName;
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
