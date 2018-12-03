package com.zhou.netty.ch2.client;

import com.zhou.netty.ch2.protocol.Packet;
import com.zhou.netty.ch2.protocol.PacketCodeC;
import com.zhou.netty.ch2.protocol.command.request.LoginRequestCommand;
import com.zhou.netty.ch2.protocol.command.response.LoginResponsePacket;
import com.zhou.netty.ch2.protocol.command.response.MessageResponsePacket;
import com.zhou.netty.ch2.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

/**
 * @author liqingzhou on 18/9/30
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(new Date() + "客户端开始登录");
        // 准备数据
        ByteBuf buffer = getByteBuf(ctx);
//        System.out.println(buffer.toString(Charset.defaultCharset()));
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        // 解码
        System.out.println(byteBuf.toString(Charset.defaultCharset()));
        Packet decode = PacketCodeC.decode(byteBuf);
        if (decode instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) decode;
            if (loginResponsePacket.getStatus() == 0) {
                LoginUtil.markAsLogin(ctx.channel());
                System.out.println(new Date() + ":客户端登录成功");
            } else {
                System.out.println(new Date() + ":客户端登录失败，原因：" + loginResponsePacket.getMessage());
            }
        } else if (decode instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) decode;
            System.out.println(new Date() + ":收到服务器消息 -> " + messageResponsePacket.getMessage());
        }

    }

    public ByteBuf getByteBuf(ChannelHandlerContext cx) {
        ByteBuf buffer = cx.alloc().buffer();
        LoginRequestCommand requestCommand = new LoginRequestCommand();
        requestCommand.setUserId(UUID.randomUUID().toString());
        requestCommand.setUserName("zhou");
        requestCommand.setPassword("pwd");
        ByteBuf encode = PacketCodeC.encode(requestCommand);
        buffer.writeBytes(encode);
        return buffer;
    }
}
