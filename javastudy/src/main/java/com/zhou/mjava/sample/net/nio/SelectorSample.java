package com.zhou.mjava.sample.net.nio;

import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by liqingzhou on 17/8/19.
 */

/**
 * http://www.bijishequ.com/detail/434023?p=
 */
public class SelectorSample {
    public static void f1(){
        try {
            //通过调用Selector.open()方法创建一个Selector，如下：
            Selector selector = Selector.open();
            SocketChannel socketChannel = null;// todo init
            //将chanel注册到selector上，并注册感兴趣的事件
            SelectionKey key  = socketChannel.register(selector, SelectionKey.OP_READ);
            //如果你对不止一种事件感兴趣，那么可以用 “ 位 或 ” 操作符将常量连接起来，如下：
            //int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
           key = socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("key.interestOps() = " + key.interestOps());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
