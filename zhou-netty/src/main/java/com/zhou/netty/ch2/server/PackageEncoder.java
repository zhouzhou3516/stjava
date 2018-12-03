package com.zhou.netty.ch2.server;

import com.zhou.netty.ch2.protocol.Packet;
import com.zhou.netty.ch2.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author liqingzhou on 18/10/15
 */

public class PackageEncoder extends MessageToByteEncoder<Packet> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet,
            ByteBuf byteBuf) throws Exception {
        PacketCodeC.encode(byteBuf, packet);
    }
}
