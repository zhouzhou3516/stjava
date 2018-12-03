package com.zhou.mjava.sample.net.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liqingzhou on 17/8/19.
 */
public class SocketClient {

    public static final String IP_ADDR = "localhost";//服务器地址
    public static final int PORT = 7777;//服务器端口号
    public static Logger logger = LoggerFactory.getLogger(SocketClient.class);

    public static void main(String[] args) {
        System.out.println("客户端启动...");
        for (int i = 0; i < 1; i++) {
            SocketClientHandler clientHandler = new SocketClientHandler();
            clientHandler.start();
        }
    }

    public static class SocketClientHandler implements Runnable {

        public SocketClientHandler() {
        }

        public void start() {
            new Thread(this).start();
        }

        @Override
        public void run() {
            Socket socket = null;
            try {
                //创建一个流套接字并将其连接到指定主机上的指定端口号
                socket = new Socket(IP_ADDR, PORT);
                //读取服务器端数据
                DataInputStream input = new DataInputStream(socket.getInputStream());
                //向服务器端发送数据
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("Please enter any String to send to SocketServer.\n"
                        + "Input [Done] to stop the socket");

                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String next = scanner.next();
                    out.writeUTF(next);
                    String ret = input.readUTF();
                    System.out.println("服务器端返回过来的是: " + ret);
                    if(StringUtils.equals(ret,"Done")){
                        logger.info("关闭连接");
                        break;
                    }
                }
                out.close();
                input.close();

            } catch (Exception e) {
                logger.error("Client Exception", e);
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        socket = null;
                        System.out.println("客户端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }
    }
}
