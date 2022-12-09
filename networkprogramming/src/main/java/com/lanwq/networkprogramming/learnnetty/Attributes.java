package com.lanwq.networkprogramming.learnnetty;

import io.netty.util.AttributeKey;

/**
 * @author Vin lan
 * @className Attribute
 * @description TODO
 * @createTime 2022-12-09  12:00
 **/
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
