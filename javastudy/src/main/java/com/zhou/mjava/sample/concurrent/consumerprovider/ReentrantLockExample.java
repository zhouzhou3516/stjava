package com.zhou.mjava.sample.concurrent.consumerprovider;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用(ReentrantLock，Condition.await Condition.signal)来实现生产者消费者模式
 * (Sychronized wait notify) 方式有可能造成资源浪费：
 * 例如生产者在缓冲区满的时候调用wait()进入休眠，此时会唤醒另一个线程,而此线程有可能是生产者,还要再休眠，多尴尬。
 * Condition就可以解决这个问题，保证每次唤醒的都是消费者。
 *
 * @author liqingzhou on 18/2/13
 */
public class ReentrantLockExample {

    private static Logger logger = LoggerFactory.getLogger(ReentrantLockExample.class);
    private static ReentrantLock lock = new ReentrantLock();
    // 生产者 等待 notFull
    private static Condition notFull = lock.newCondition();
    // 消费者等待 notEmpty
    private static Condition notEmpty = lock.newCondition();
    private static PriorityQueue<Integer> queue = new PriorityQueue<>(100);

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockExample condition = new ReentrantLockExample();
        Thread p1 = new Thread(new ReentrantLockExample.Provider(lock, queue));
        Thread c1 = new Thread(new Consumer(lock, queue));
        Thread c2 = new Thread(new Consumer(lock, queue));
        c1.start();
        c2.start();
        Thread.sleep(1);
        p1.start();
    }


    static class Provider implements Runnable {

        private final ReentrantLock lock;
        private final PriorityQueue<Integer> queue;

        public Provider(ReentrantLock lock, PriorityQueue queue) {
            this.lock = lock;
            this.queue = queue;
        }

        @Override
        public void run() {
            //while (true) {
            lock.lock();
            logger.info("Provider {} 获取锁", Thread.currentThread().getName());
            try {
                while (queue.size() == 100) {
                    System.out.println("队列满了，等待中。。。。。。");
                    try {
                        notFull.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("生产了一个。");
                queue.offer(RandomUtils.nextInt(1, 100));
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
            //}
        }
    }

    static class Consumer implements Runnable {

        private final ReentrantLock lock;
        private final PriorityQueue<Integer> queue;

        public Consumer(ReentrantLock lock, PriorityQueue queue) {
            this.lock = lock;
            this.queue = queue;
        }

        @Override
        public void run() {
            //while (true) {
            lock.lock();
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /**
             * lock.lock() 拿不到锁就死等
             * lock.tryLock() 马上返回,不死等。拿到锁返回true，拿不到返回false
             * lock.lockInterruptibly ::等锁的状态下，可以被中断。别的线程调用此线程的 interrupt()方法中断此线程
             */
            logger.info("Consumer {}，获取锁", Thread.currentThread().getName());

            try {
                while (queue.size() == 0) {
                    System.out.println("队列空，等待。。。。。。");
                    notEmpty.await();
                }
                System.out.println("消费了一个。" + queue.poll());
                notFull.signal();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        //}
    }


}


