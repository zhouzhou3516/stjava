package com.zhou.netty.ch2.server;

import com.zhou.netty.ch2.protocol.PacketCodeC;
import com.zhou.netty.ch2.protocol.command.request.MessageRequestPacket;
import com.zhou.netty.ch2.protocol.command.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;

/**
 * @author liqingzhou on 18/10/15
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
            MessageRequestPacket messageRequestPacket) throws Exception {
        System.out.println(new Date() + ": 收到客户端消息->" + messageRequestPacket.getMessage());
        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setStatus(0);
        responsePacket.setMessage("服务器端回复消息");
        ByteBuf byteBuf = PacketCodeC.encode(responsePacket);
        channelHandlerContext.channel().writeAndFlush(byteBuf);
    }
}
