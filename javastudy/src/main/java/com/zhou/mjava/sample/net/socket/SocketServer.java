package com.zhou.mjava.sample.net.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liqingzhou on 17/8/19.
 */
public class SocketServer {

    private static final int PORT = 7777;
    Logger logger = LoggerFactory.getLogger(SocketServer.class);

    public static void main(String[] args) {
        new SocketServer().init();
    }

    public void init() {
        System.out.println("init socketserver");
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                // 对accept()方法的调用将被阻塞，直到一个连接建立
                Socket client = serverSocket.accept();
                System.out.println("收到客户端连接");
                // 1. BIO方式 同步-阻塞： 用一个线程处理一个Client ##连接##
                ServerHandler serverHandler = new ServerHandler(client);
                serverHandler.start();
/*
                2. 伪 异步I/O 编程：少量的线程（1个或多个）处理 N 个Client连接。但是底层还是使用的 同步阻塞I/O
                Executor executorService = Executors.newFixedThreadPool(60);//60个线程
                executorService.execute(new ServerHandler(client));
                 -1）同步和异步是针对应用程序和内核的交互而言的。
                 -2）阻塞和非阻塞是针对于进程在访问数据的时候，根据IO操作的就绪状态来采取的不同方式，
                     说白了是一种读取或者写入操作函数的实现方式，阻塞方式下读取或者写入函数将一直等待，而非阻塞方式下，读取或者写入函数会立即返回一个状态值。
                3. NIO
                传统的IO操作，比如read()，当没有数据可读的时候，线程一直阻塞被占用，直到数据到来。NIO中没有数据可读时，read()会立即返回0，线程不会阻塞。

                NIO Chanel 与 流的区别:
                   1）双向，通道即可读也可写，InputStream和OutputStream 是单向
                   2）通道可以异步读写
                   3）通道必需结合Buffer使用
                Channel 分类(File I/O和Stream I/O): FileChannel=>文件读写 SocketChannel=>TCP读写

*/
            }
        } catch (Exception e) {
            System.out.println("服务器异常: " + e.getMessage());
        }
    }


    private class ServerHandler implements Runnable {

        DataInputStream input;
        DataOutputStream outputStream;
        private Socket socket;

        public ServerHandler(Socket client) {
            socket = client;
        }

        public void start() {
            new Thread(this).start();
        }

        public void run() {
            try {
                input = new DataInputStream(socket.getInputStream());
                String clientInputStr;
                while ((clientInputStr = input.readUTF()) != null) {
                    logger.info("message from client:{}" , clientInputStr);
                    if (StringUtils.equals(clientInputStr, "Done")) {
                        outputStream.writeUTF("Done");
                        logger.info("收到Done,服务器端关闭该连接");
                        break;
                    }
                    outputStream = new DataOutputStream(socket.getOutputStream());
                    outputStream.writeUTF("Server received msg :" + clientInputStr);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
