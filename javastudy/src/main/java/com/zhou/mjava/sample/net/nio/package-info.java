/**
 * Created by liqingzhou on 17/8/19.
 */
package com.zhou.mjava.sample.net.nio;

/**
 * 入门参考：http://www.bijishequ.com/detail/434023?p=
 *
 * java nio 简明教程 比较全面
 * https://java-nio.avenwu.net/java-nio-non-blocking-server.html

阻塞：当一个流没有数据返回时，当前线程无法 去读取其他的流。所以每一次链接都要建立一个线程
  每个线程需要为堆栈分配320KB（32位JVM）到1024KB(64位JVM)1M  1000 000 *1M=1000G=1TB
 */