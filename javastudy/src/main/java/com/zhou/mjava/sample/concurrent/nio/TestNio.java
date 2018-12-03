package com.zhou.mjava.sample.concurrent.nio;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author liqingzhou on 17/12/6
 */
public class TestNio {

    private static final ExecutorService SERVICE = Executors.newCachedThreadPool();

    /**
     * 同步：多个任务顺序执行
     * 异步：多个任务同时执行。通过future实现了异步
     * 阻塞：future虽然可以实现异步执行，但是调用future 得get方法会阻塞主线程
     * 非阻塞：listenableFuture可以给future添加回调，使得主线程不被阻塞
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread thread = Thread.currentThread();
        /**
         *
         * 这一步实现了 stringTask异步执行
         */
        Future<String> stringTask = SERVICE.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Thread:" + Thread.currentThread().getName() + " sleep 3s...");
                Thread.sleep(3000);
                System.out.println("Main thread:" + thread.getState());
                Thread.sleep(3000);
                return "Hello World";
            }
        });
        // FutureTask

        System.out.println(
                "Thread.currentThread().getState() = " + Thread.currentThread().getState());
        String result = stringTask.get();//这一步调用，将会阻塞Main 线程
        System.err.println("StringTask: " + result);
    }

}
