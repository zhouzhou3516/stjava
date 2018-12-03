package com.zhou.netty.ch1.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author liqingzhou on 18/9/30
 */
public class NettyServer {

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                .group(boosGroup, workGroup)//线程组
                .channel(NioServerSocketChannel.class)//线程模型
                //服务channel启动时需要进行一些逻辑，一般用不到
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel nioServerSocketChannel)
                            throws Exception {
                        System.out.println("服务端启动中");

                    }

                })
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new FirstServerHandler());

                    }
                });
        bootstrap.bind(8000);
    }
    /**
     * 1. handler() vs childHandler()
     * handler()用于指定在服务器端启动过程中的一些逻辑，通常用不到
     * childHandler()踊跃置顶处理新连接数据的读写处理逻辑
     *
     * 2. attr() vs childAttr()
     * attr()用于给服务器channel也就是NioServerSocketChannel指定自定义属性
     * childAttr()可以给每一条连接也就是NioSocketChannel指定自定义属性，然后后续我们可以通过channel.attr()读取该属性
     * serverBootstrap.attr(AttributeKey.newInstance("serverName","nettyServer")
     * serverBootstrap.childAttr(AttributeKey.newInstance("clientKey","clientValue")
     *
     * 3. option() vs childOption()
     * option()用于给服务端channel设置一些属性
     * childOption用于给每条连接设置一些TCP底层相关的属性
     * serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE,true)-是否开启TCP底层信条机制
     * m
     *
     */

}
