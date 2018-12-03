package com.zhou.mjava.sample.concurrent.consumerprovider;

import java.util.PriorityQueue;
import org.apache.commons.lang3.RandomUtils;

/**
 * 使用sychronized关键字和Object.wait()、Object.notify()实现生产者-消费者模式
 *
 * @author liqingzhou on 18/2/7
 */
public class SychronizedExample {

    private static PriorityQueue<Integer> queue = new PriorityQueue<>(100);

    public static void main(String[] args) throws InterruptedException {

        Thread consumer1 = new Thread(new Consumer(queue));
        Thread consumer2 = new Thread(new Consumer(queue));
        Thread provider = new Thread(new Provider(queue));
        consumer1.start();
        Thread.sleep(1000);
        consumer2.start();
        Thread.sleep(1000);
        provider.start();
    }

    public PriorityQueue<Integer> getQueue() {
        return queue;
    }

    public void setQueue(PriorityQueue<Integer> queue) {
        this.queue = queue;
    }

    static class Consumer implements Runnable {

        private final PriorityQueue<Integer> queue;

        public Consumer(PriorityQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            //while (true) {
            synchronized (queue) {
                //永远在循环（loop）里调用 wait 和 notify，不是在 If 语句
                while (queue.size() == 0) { // if 会出错，用if 当 queue.wait() 等到锁的后就直接往下执行了，而没有判断queue.size是不是为空
                    try {
                        System.out.println("queue is empty!");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        queue.notify();
                    }
                }
                if (queue.isEmpty()) {
                    System.out.println("唤醒了一个空的线程,所以应该用while而不是if包含wait()");
                } else {
                    System.out.println("可以获取元素");
                    System.out.println(queue.poll());
                }
                queue.notify();
            }
            //}
        }
    }


    static class Provider implements Runnable {

        private final PriorityQueue<Integer> queue;

        public Provider(PriorityQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            // while (true) {
            synchronized (queue) {

                while (queue.size() == 100) {
                    try {
                        System.out.println("队列满了，等待中。。。。。。");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("生产了一个。");
                queue.offer(RandomUtils.nextInt(1, 100));
                queue.notify();
            }
            //  }
        }
    }
}
