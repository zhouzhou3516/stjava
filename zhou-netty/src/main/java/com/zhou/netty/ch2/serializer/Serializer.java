package com.zhou.netty.ch2.serializer;

/**
 * @author liqingzhou on 18/10/12
 */
public interface Serializer {


    Serializer DEFAULT = new JsonSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * 序列化,java对象转为二进制
     */
    byte[] serialize(Object obj);

    /**
     * 反序列化,二进制转java对象
     */
    <T> T deserialize(Class<T> clz, byte[] bytes);

}
