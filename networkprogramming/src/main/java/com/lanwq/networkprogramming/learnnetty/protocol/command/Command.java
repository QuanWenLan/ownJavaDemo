package com.lanwq.networkprogramming.learnnetty.protocol.command;

/**
 * @author Vin lan
 * @className Command
 * @description TODO
 * @createTime 2022-12-08  17:12
 **/
public interface Command {
    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
}
