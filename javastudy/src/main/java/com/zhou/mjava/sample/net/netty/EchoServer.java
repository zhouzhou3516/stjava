package com.zhou.mjava.sample.net.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;

/**
 * @author liqingzhou on 18/7/15
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        EchoServer echoServer = new EchoServer(7778);
        echoServer.start();
    }

    private void start() throws InterruptedException {
        final EchoServerHandler handler = new EchoServerHandler("first");//实现业务逻辑,只关心io完成后处理数据 or
        // 有异常

        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();// 创建server实例
            bootstrap
                    .group(group)//EventLoopGroup进行事件处理:接受新连接,读/写操作
                    .channel(NioServerSocketChannel.class)//channel类型
                    .localAddress(new InetSocketAddress(port))//port
                    .childHandler(new ChannelInitializer() {//新连接到来时，会创建一个新的Channel,
                        // channelInitializer把EchoServerHandler实例添加到该channel的channelPipline中。
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast("handler2", new EchoServerHandler("second"));
                        }
                    });
            ChannelFuture future = bootstrap
                    .bind()//绑定服务器
                    // Waits for this future until it is done,
                    .sync();//阻塞当前现线程直到绑定完成

            future.channel().closeFuture()//关闭channel
                    .sync();//阻塞直到关闭完成
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
