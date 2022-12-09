package com.lanwq.networkprogramming.learnnetty.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.lanwq.networkprogramming.learnnetty.serialize.Serializer;
import com.lanwq.networkprogramming.learnnetty.serialize.SerializerAlgorithm;

/**
 * @author Vin lan
 * @className JSONSerializer
 * @description
 * @createTime 2022-12-08  17:20
 **/
public class JSONSerializer implements Serializer {
    @Override
    public Byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
