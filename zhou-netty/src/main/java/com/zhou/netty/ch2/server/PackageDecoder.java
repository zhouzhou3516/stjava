package com.zhou.netty.ch2.server;

import com.zhou.netty.ch2.protocol.Packet;
import com.zhou.netty.ch2.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * @author liqingzhou on 18/10/15
 */
public class PackageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf,
            List<Object> list) throws Exception {
        Packet decode = PacketCodeC.decode(byteBuf);
        list.add(decode);
    }
}
