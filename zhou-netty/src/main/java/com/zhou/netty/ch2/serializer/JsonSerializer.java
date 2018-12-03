package com.zhou.netty.ch2.serializer;

import com.alibaba.fastjson.JSON;

/**
 * @author liqingzhou on 18/10/12
 */
public class JsonSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(Class<T> clz, byte[] bytes) {
        return JSON.parseObject(bytes, clz);
    }
}
