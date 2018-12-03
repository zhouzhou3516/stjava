package com.zhou.mjava.sample.net.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by liqingzhou on 17/8/19.
 */

/**
 * channel: 文件通道，socket通道（tcp），datagram通道（udp），管道
 */
public class FileChannelSample {

    public static void main(String[] args) {

        try {
            RandomAccessFile file = new RandomAccessFile("./ba.txt", "rw");
            /*
                因为 FileChannel 不能切换到非阻塞模式。而套接字通道SocketChannel可以。
                Channel与 Selector 一起使用时，Channel 必须处于非阻塞模式下。这意味着不能将 FileChannel 与 Selector 一起使用，
            */
            FileChannel fileChannel = file.getChannel();

             /*Buffer 参数
             * 1. capacity 物理大小
             * 2. limit：读buffer的limit = position ， 写模式的limit = capacity
             * 3. position：当前位置
             * */
            ByteBuffer buffer = ByteBuffer.allocate(100);
            int byteSize = fileChannel.read(buffer);
            System.out.println("byteSize=" + byteSize);
            while(byteSize!=-1){
                //首先读取数据到Buffer，然后flip翻转buffer，从buffer中读取数据，即把bufferindex指向开始
                buffer.flip();
                while (buffer.hasRemaining()){
                    System.out.print(buffer.getChar());
                }
                buffer.clear();
                byteSize = fileChannel.read(buffer);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
