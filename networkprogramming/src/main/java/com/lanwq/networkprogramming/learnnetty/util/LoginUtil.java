package com.lanwq.networkprogramming.learnnetty.util;

import com.lanwq.networkprogramming.learnnetty.attributes.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author Vin lan
 * @className LoginUtil
 * @description
 * @createTime 2022-12-09  12:04
 **/
public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
