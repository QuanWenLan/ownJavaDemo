package com.lanwq.networkprogramming.learnnetty.protocol;

import lombok.Data;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Vin lan
 * @className Packet
 * @description 数据包
 * @createTime 2022-12-08  17:05
 * 结构
 * xxxx x x x xxxx xxx...
 * 魔数（4字节），版本号（1字节），序列化算法（1字节），指令（1字节），数据长度（4字节）数据（N字节）
 **/
@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    protected Byte version = 1;
    /**
     * 数据包的指令，登录或者登出
     * @return 指令
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
