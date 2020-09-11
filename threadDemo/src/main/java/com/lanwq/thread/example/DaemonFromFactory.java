package com.lanwq.thread.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DaemonFromFactory
 * @Description 通过ThreadFactory创建后台线程
 * @Author lanwenquan
 * @Date 2020/05/29 15:20
 */
public class DaemonFromFactory implements Runnable{

    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for (int i = 0; i < 10; i++) {
            executorService.execute(new DaemonFromFactory());
        }
        System.out.println("all daemons started");
        TimeUnit.MILLISECONDS.sleep(500);
    }
}
