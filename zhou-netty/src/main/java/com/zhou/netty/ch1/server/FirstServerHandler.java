package com.zhou.netty.ch1.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author liqingzhou on 18/9/30
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ":服务器读到数据->" + byteBuf.toString(CharsetUtil.UTF_8));
        //回复数据到客户端
        System.out.println(new Date() + ": 回复数据到客户端");
        ByteBuf out = getByteBuf(ctx);
        ctx.channel().writeAndFlush(out);
    }

    public ByteBuf getByteBuf(ChannelHandlerContext cx) {
        ByteBuf buffer = cx.alloc().buffer();
        buffer.writeBytes("Hello，欢迎来到netty".getBytes(Charset.forName("UTF-8")));
        return buffer;
    }
}
