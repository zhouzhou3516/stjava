package com.zhou.mjava.sample.concurrent.lock;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liqingzhou on 18/9/27
 */
public class SynVsCAS {

    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static int counter = 0;

    public static void main(String[] args) {

        synlock();


    }
    public static void synlock(){
        List<Thread> tasks = Lists.newLinkedList();
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        CyclicBarrier barrier = new CyclicBarrier(2000, new Runnable() {
            @Override
            public void run() {
                stopwatch.stop();
                long elapsed = stopwatch.elapsed(TimeUnit.MICROSECONDS);
                System.out.println(elapsed);
                System.out.println(counter);
            }
        });
        for (int i = 0; i < 2000; i++) {
            tasks.add(new Thread(new IncrSyncTask(barrier)));
        }
        stopwatch.start();
        for (int i = 0; i < 2000; i++) {
            tasks.get(i).start();
        }
    }
    public static void cas(){
        List<Thread> tasks = Lists.newLinkedList();
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        CyclicBarrier barrier = new CyclicBarrier(2000, new Runnable() {
            @Override
            public void run() {
                stopwatch.stop();
                long elapsed = stopwatch.elapsed(TimeUnit.MICROSECONDS);
                System.out.println(elapsed);
                System.out.println(atomicInteger);
            }
        });
        for (int i = 0; i < 2000; i++) {
            tasks.add(new Thread(new IncrCasTask(barrier)));
        }
        stopwatch.start();
        for (int i = 0; i < 2000; i++) {
            tasks.get(i).start();
        }
    }

    public static class IncrCasTask implements Runnable {

        CyclicBarrier barrier;

        public IncrCasTask(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            atomicInteger.incrementAndGet();
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
    public static class IncrSyncTask implements Runnable {

        CyclicBarrier barrier;

        public IncrSyncTask(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            synchronized (SynVsCAS.class){
                counter++;
            }
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}
