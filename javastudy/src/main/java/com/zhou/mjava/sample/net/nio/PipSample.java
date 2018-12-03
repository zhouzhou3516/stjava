package com.zhou.mjava.sample.net.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Created by liqingzhou on 17/8/19.
 *
 * Java NIO 管道是2个线程之间的单向数据连接。Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取。
 *
 *              \               Pipe         \
 * ThreadA  ->  \ sinkChannel-> sourceCHannel\  ->  ThreadB
 */
public class PipSample {

    public static void main(String[] args) {
        Pipe pipe = null;
        try {
            //获取管道
            pipe = Pipe.open();
            //获取Sink 管道
            Pipe.SinkChannel sinkChannel = pipe.sink();
            //需要写入数据
            String newData = "New String to write to file..." + System.currentTimeMillis();
//新建缓存区
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            //缓存区存放数据
            buf.put(newData.getBytes());
            buf.flip();
            while(buf.hasRemaining()) {
                sinkChannel.write(buf);
            }

            //获取Source 管道
            Pipe.SourceChannel sourceChannel = pipe.source();
            ByteBuffer buf2 = ByteBuffer.allocate(48);
            int bytesRead = sourceChannel.read(buf2);
            while (bytesRead != -1) {
                System.out.println("Read " + bytesRead);
//buf.flip()的调用，首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据（注：flip：空翻，反转）
                buf.flip();
//判断是否有剩余（注：Remaining：剩余的）
                while(buf.hasRemaining()){
                    System.out.print((char) buf.get());
                }
                buf.clear();
                bytesRead = sourceChannel.read(buf);
            }
            sourceChannel.close();
            sinkChannel.close();





        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
