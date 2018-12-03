package com.zhou.mjava.effectJava.chapter3;

/**
 * @author liqingzhou on 18/4/2
 */
public class NoVisibility {

    private static boolean ready;
    private static int number;

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(100);
        number = 42;
        System.out.println("number=42");
        ready = true;
    }

    private static class ReaderThread extends Thread {

        public void run() {
            while (!ready) {
                System.out.println("yield");
                Thread.yield();
            }
            System.out.println("Number:" + number);
        }
    }

}
