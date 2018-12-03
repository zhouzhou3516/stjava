package com.zhou.mjava.sample.net.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author liqingzhou on 18/7/15
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    //连接建立
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks", CharsetUtil.UTF_8));//连接建立后，发送一条消息
    }

    //收到消息
    //服务器发的一条消息可能被分次接受。一次发了5字节，可能一次read3字节，一次read2字节
    // 不能保证5字节被一次性接受
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("client received：" + msg.toString(CharsetUtil.UTF_8));

    }

    //异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
