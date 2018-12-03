package com.zhou.netty.ch1.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author liqingzhou on 18/9/30
 */
public class NettyClient {

    private static final int MAX_RETRY = 6;

    public static void main(String[] args) {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new FirstClientHandler());
                    }
                });
        connect(bootstrap, "localhost", 8000, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String hostname, int port, int retry) {
        bootstrap.connect(hostname, port).addListener((ChannelFutureListener) channelFuture -> {
            if (channelFuture.isSuccess()) {
                System.out.println("连接成功");
            } else if (retry == 0) {
                System.out.println("连接失败次数用完，放弃重连");
            } else {
                int order = MAX_RETRY - retry + 1;//重试次数
                int delay = 1 << order;
                System.out.println(new Date() + ":连接失败，第" + order + "次重连");
                bootstrap.group()
                        .schedule(() -> connect(bootstrap, hostname, port, retry - 1), delay,
                                TimeUnit
                                        .SECONDS);
            }
        });
    }

}
