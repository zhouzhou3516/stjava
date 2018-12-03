package com.zhou.mjava.leetcode;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 100w 大数组求和
 *
 * @author liqingzhou on 18/9/20
 */
public class Sum100WArray {

    public static void main(String[] args) throws InterruptedException {
        List<CounterTask> list = Lists.newArrayList();
        ExecutorService executor = Executors.newCachedThreadPool();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                int total = 0;
                for (CounterTask task : list) {
                    int sum = task.getSum();
                    total += sum;
                }
                System.out.println("total:" + total);
            }
        });
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) {
            arr[i] = 1;
        }
        int step = 1000;
        for (int i = 0; i < 10; i++) {
            int start = i * step;
            int end = (i + 1) * step;
            CounterTask task = new CounterTask(arr, cyclicBarrier, start, end);
            list.add(task);
            executor.submit(task);

        }
    }

    public static class CounterTask implements Callable<Integer> {

        private CyclicBarrier barrier;
        private int start;
        private int end;
        private int sum;
        private int arr[];


        public CounterTask(int[] arr, CyclicBarrier barrier, int start, int end) {
            this.barrier = barrier;
            this.start = start;
            this.end = end;
            this.arr = arr;
        }

        @Override
        public Integer call() throws Exception {
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            barrier.await();
            return sum;
        }

        public int getSum() {
            return sum;
        }
    }
}
