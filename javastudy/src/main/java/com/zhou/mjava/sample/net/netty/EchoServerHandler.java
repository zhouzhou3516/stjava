package com.zhou.mjava.sample.net.netty;

import static io.netty.util.CharsetUtil.UTF_8;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liqingzhou on 18/7/15
 * 一个channelpipline可以有多个channelHandler，但一个超类型的只能有一个。比如pipline中可以有inboundHandler，outboundhandler
 * ，但只能有一个类型的inboundHandler.未验证
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    static Logger logger = LoggerFactory.getLogger(EchoServerHandler.class);

    private String name;

    public EchoServerHandler(String name) {
        this.name = name;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        logger.info("Server {} received:{}", name, in.toString(UTF_8));
        //ctx.write(in);//要把消息回写给客户端.  在channelRead()
        // 执行完后，异步回写，需要持有byteBuf in的引用,ChannelInboundHandlerAdapter正式不会释放msg
        // 而SimpleChannelInboundHandler执行完channelRead后会释放 消息引用。
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelReadComplete()} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ChannelFuture channelFuture = ctx
                .writeAndFlush(Unpooled.EMPTY_BUFFER);//wreite and read 是个异步,返回future
        channelFuture.addListener(ChannelFutureListener.CLOSE);
    }


}
