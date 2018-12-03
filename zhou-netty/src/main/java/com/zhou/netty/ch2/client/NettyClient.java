package com.zhou.netty.ch2.client;

import com.zhou.netty.ch2.protocol.PacketCodeC;
import com.zhou.netty.ch2.protocol.command.request.MessageRequestPacket;
import com.zhou.netty.ch2.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.Date;
import java.util.Scanner;
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
                        channel.pipeline().addLast(new ClientHandler());
                    }
                });
        connect(bootstrap, "localhost", 8000, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String hostname, int port, int retry) {
        bootstrap.connect(hostname, port).addListener((ChannelFutureListener) channelFuture -> {
            if (channelFuture.isSuccess()) {
                System.out.println("连接成功");
                startConsoleThread(channelFuture.channel());
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

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtil.hasLogin(channel)) {
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.nextLine();

                    MessageRequestPacket packet = new MessageRequestPacket();
                    packet.setMessage(line);
                    ByteBuf byteBuf = PacketCodeC.encode(packet);
                    channel.writeAndFlush(byteBuf);
                }
            }
        }).start();
    }

}
