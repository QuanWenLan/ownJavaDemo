package com.lanwq.networkprogramming.learnnetty.chat.attribute;

import io.netty.util.AttributeKey;
import com.lanwq.networkprogramming.learnnetty.chat.session.Session;

public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
