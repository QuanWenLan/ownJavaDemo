package com.lanwq.thread.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SleepingTask
 * @Description 休眠线程
 * @Author lanwenquan
 * @Date 2020/05/28 16:51
 */
public class SleepingTask extends LiftOff {
    @Override
    public void run() {
        try {
            while (countDown-- > 0) {
                System.out.print(status());
                // 老的写法
//                Thread.sleep(100);
                // 新的写法 Java SE5/6, 任务中止执行给定的时间
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            cachedThreadPool.submit(new SleepingTask());
        }
        cachedThreadPool.shutdown();
    }
}