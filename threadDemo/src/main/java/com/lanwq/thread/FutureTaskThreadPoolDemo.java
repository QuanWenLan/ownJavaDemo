package com.lanwq.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Lan
 * @createTime 2023-12-02  15:41
 **/
public class FutureTaskThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task1 over";
        });
        pool.submit(futureTask1);

        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task2 over";
        });
        pool.submit(futureTask2);
        futureTask1.get();
        futureTask2.get();

        FutureTask<String> futureTask3 = new FutureTask<>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task3 over";
        });
        // 超时会抛出异常：
        /**
         * Exception in thread "main" java.util.concurrent.TimeoutException
         * 	at java.util.concurrent.FutureTask.get(FutureTask.java:205)
         * 	at com.lanwq.thread.FutureTaskThreadPoolDemo.main(FutureTaskThreadPoolDemo.java:47)
         */
        futureTask3.get(200, TimeUnit.MILLISECONDS);
        pool.submit(futureTask3);

        pool.shutdown();
    }
}
