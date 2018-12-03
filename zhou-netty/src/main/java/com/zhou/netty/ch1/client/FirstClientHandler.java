package com.zhou.netty.ch1.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import java.util.Date;

/**
 * @author liqingzhou on 18/9/30
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "客户端写出数据");
        // 准备数据
        ByteBuf buffer = getByteBuf(ctx);
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ":收到服务器端回复 ->" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    public ByteBuf getByteBuf(ChannelHandlerContext cx) {
        ByteBuf buffer = cx.alloc().buffer();
        buffer.writeBytes("Hello World！".getBytes(CharsetUtil.UTF_8));
        return buffer;
    }
}
