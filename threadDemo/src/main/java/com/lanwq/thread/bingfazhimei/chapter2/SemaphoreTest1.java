package com.lanwq.thread.bingfazhimei.chapter2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Vin lan
 * @className SemaphoreTest1
 * @description
 * @createTime 2021-11-10  10:21
 **/
public class SemaphoreTest1 {
    /**
     * 创建一个 Semaphore 实例
     */
    private static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
            System.out.println(Thread.currentThread() + " over1");
            semaphore.release();
            System.out.println(Thread.currentThread() + " over2 next");
        });

        executorService.submit(()->{
            System.out.println(Thread.currentThread() + " over2");
            semaphore.release();
            System.out.println(Thread.currentThread() + " over2 next");
        });
        semaphore.acquire(2);
        System.out.println("all child thread over");
        executorService.shutdown();
    }
}
