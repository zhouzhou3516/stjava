package com.zhou.netty.ch2.protocol;

import com.google.common.collect.Maps;
import com.zhou.netty.ch2.protocol.command.CommandCode;
import com.zhou.netty.ch2.protocol.command.request.LoginRequestCommand;
import com.zhou.netty.ch2.protocol.command.request.MessageRequestPacket;
import com.zhou.netty.ch2.protocol.command.response.LoginResponsePacket;
import com.zhou.netty.ch2.protocol.command.response.MessageResponsePacket;
import com.zhou.netty.ch2.serializer.JsonSerializer;
import com.zhou.netty.ch2.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import java.util.Map;

/**
 * 包编解码器
 *
 * @author liqingzhou on 18/10/12
 */
public class PacketCodeC {

    private static final int MAGIC_NUMBER = 0x12345678;
    private static final Map<Byte, Class<? extends Packet>> commandTypeMap = Maps.newHashMap();
    private static final Map<Byte, Serializer> serializerMap = Maps
            .newHashMap();

    static {
        commandTypeMap.put(CommandCode.LOGIN_REQUEST, LoginRequestCommand.class);
        commandTypeMap.put(CommandCode.LOGIN_RESPONSE, LoginResponsePacket.class);
        commandTypeMap.put(CommandCode.MESSAGE_REQUEST, MessageRequestPacket.class);
        commandTypeMap.put(CommandCode.MESSAGE_RESPONSE, MessageResponsePacket.class);

        JsonSerializer jsonSerializer = new JsonSerializer();
        serializerMap.put(jsonSerializer.getSerializerAlgorithm(), jsonSerializer);
    }

    public static void encode(ByteBuf byteBuf, Packet packet) {
        // 2. 序列化
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 3. 编码
        /**
         *
         *  magic code | version| serializer algorithm | command code |length | body
         *   4 bytes   | 1 byte | 1 byte               | 1 byte       |4 byte | ?
         */
        byteBuf.writeInt(MAGIC_NUMBER)
                .writeByte(packet.getVersion())
                .writeByte(Serializer.DEFAULT.getSerializerAlgorithm())
                .writeByte(packet.getCommand())
                .writeInt(bytes.length)
                .writeBytes(bytes);
    }

    /**
     *
     * @param packet
     * @return
     */
    public static ByteBuf encode(Packet packet) {
        // 1. 创建 ByteBuf对象
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.ioBuffer();
        // 2. 序列化
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 3. 编码
        /**
         *
         *  magic code | version| serializer algorithm | command code |length | body
         *   4 bytes   | 1 byte | 1 byte               | 1 byte       |4 byte | ?
         */
        byteBuf.writeInt(MAGIC_NUMBER)
                .writeByte(packet.getVersion())
                .writeByte(Serializer.DEFAULT.getSerializerAlgorithm())
                .writeByte(packet.getCommand())
                .writeInt(bytes.length)
                .writeBytes(bytes);
        return byteBuf;
    }

    public static Packet decode(ByteBuf byteBuf) {
        // 跳过magic number
        byteBuf.skipBytes(4);
        // 版本号
        Byte version = byteBuf.readByte();
        // 序列化算法
        Byte serializerAlgorithm = byteBuf.readByte();
        // 命令行code
        Byte commandCode = byteBuf.readByte();

        int bodyLength = byteBuf.readInt();
        byte[] bytes = new byte[bodyLength];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(commandCode);
        Serializer serializer = getSerializer(serializerAlgorithm);
        if (requestType == null) {
            System.out.println("no requestCommandType mapped, commandCode:" + commandCode);
            return null;
        }
        if (serializer == null) {
            System.out.println("no serializer mapped, serializerAlgorithm:" + serializerAlgorithm);
            return null;
        }
        Packet packet = serializer.deserialize(requestType, bytes);
        return packet;

    }

    private static Class<? extends Packet> getRequestType(Byte commandCode) {
        return commandTypeMap.get(commandCode);
    }

    private static Serializer getSerializer(Byte serializerAlgorithm) {
        return serializerMap.get(serializerAlgorithm);
    }

}
