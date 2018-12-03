package com.zhou.mjava.sample.net;

import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

/**
 * Created by liqingzhou on 17/7/31.
 */
public class SocketClient {

    BufferedWriter bufferedWriter;
    BufferedReader reader;
    private Socket socket;

    public static void main(String[] args) {
        //多线程建立多个socket连接
        for (int i = 0; i < 10; i++) {
            String url = "localhost";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SocketClient socketClient = new SocketClient(url, 7777);
                    socketClient.writeln("Socket client thread sending msg:"+Thread.currentThread().getName()+"\r\n",true);
                    String response = socketClient.readLine();
                    System.out.println(response);
                }
            }).start();

        }
    }

    public SocketClient(InetAddress ip, int port) {
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SocketClient(String url, int port) {
        try {
            socket = new Socket(url, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeln(String message, boolean flush) {

        try {
            if (bufferedWriter == null) {
                OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
                bufferedWriter = new BufferedWriter(writer);
            }
            bufferedWriter.write(message);
            if (flush) {
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeln(String message) {

        writeln(message, false);
    }

    public List<String> readLines() {
        String line;
        List<String> lines = Lists.newArrayList();
        while ((line = readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    public String readLine() {
        try {
            if (reader == null) {
                InputStream inputStream = socket.getInputStream();
                reader = new BufferedReader(
                        new InputStreamReader(inputStream, "utf-8"));
            }
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

    public void close() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}