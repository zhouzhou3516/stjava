package com.zhou.mjava.sample.concurrent;

/**
 * Created by liqingzhou on 17/8/19.
 */
public class Sychronized {
    // 对 非static 方法加锁，锁的是实例对象
    // 对 static 方法加锁 锁的是类对象
    public static void main(String[] args) {

        Sychronized obj1 = new Sychronized();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
               obj1.fun1();
            }
        });
        t1.start();
        Sychronized obj2 = new Sychronized();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                obj2.fun2();
            }
        });
        t2.start();

    }

    public synchronized  void fun1() {

        System.out.println("fun1 start to sleep 3s");
        try {
            Thread.sleep(1000 * 3);
            System.out.println("fun1 is waiting..");

            System.out.println("fun1 is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fun1 end");
    }

    public synchronized  void fun2() {
        System.out.println("fun2 start to sleep 5s");
        try {
            Thread.sleep(1000 * 5);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fun2 end");
    }
}
