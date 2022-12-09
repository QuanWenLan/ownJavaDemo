package com.lanwq.networkprogramming.learnnetty.serialize;

import com.lanwq.networkprogramming.learnnetty.serialize.impl.JSONSerializer;

/**
 * @author Vin lan
 * @className Serializer
 * @description 数据包序列化接口
 * @createTime 2022-12-08  17:15
 **/
public interface Serializer {
    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     */
    Byte getSerializerAlgorithm();

    /**
     * Java 对象转成 二进制数据
     */
    byte[] serialize(Object object);

    /**
     * 二进制对象转成 Java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
