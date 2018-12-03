package com.zhou.mjava.sample.concurrent.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 1. 可重入锁 ReentrantLock
 *
 * 2 读写锁：ReadWriteLock
 * 多个线程同时读，只能有一个写
 * 写的时候不能同时读
 * 读的时候不能同时写
 *
 * @author liqingzhou on 18/2/13
 */
public class LockSample {

    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.readLock().lock();
                try {
                    System.out.println("获取=读=锁sleep 3s,");
                    Thread.sleep(1000 * 3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                readWriteLock.readLock().unlock();
                System.out.println("释放读锁");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {

                readWriteLock.writeLock().lock();
                try {
                    System.out.println("获取写锁sleep 3s,");
                    Thread.sleep(1000 * 3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                readWriteLock.writeLock().unlock();
                System.out.println("释放写锁");
            }
        }).start();
        Thread.sleep(100);

    }
}
