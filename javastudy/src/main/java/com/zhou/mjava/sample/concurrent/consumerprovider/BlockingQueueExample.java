package com.zhou.mjava.sample.concurrent.consumerprovider;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomUtils;

/**
 * 使用阻塞队列实现生产者-消费者模式
 * blocking queue起始就是用ReentrantLock实现的生产者消费者模式
 *
 * @author liqingzhou on 18/2/7
 */
public class BlockingQueueExample {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    public static void main(String[] args) throws InterruptedException {

        Thread consumer1 = new Thread(new Consumer(queue));
        Thread provider= new Thread(new Provider(queue));
       // provider.start();
        consumer1.start();
    }


    static class Consumer implements Runnable {

        private final BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while(true){
                try {
                   Integer intvalue =  queue.poll(10, TimeUnit.SECONDS);
                    System.out.println(intvalue);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static class Provider implements Runnable {

        private final BlockingQueue<Integer> queue;

        public Provider(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0;; i++) {
                try {
                    System.out.println("start put index:" + i);
                    queue.put(RandomUtils.nextInt(0, 100));
                    System.out.println("end put index:"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
