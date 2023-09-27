package com.lanwq.thinkinginjavademo.thread.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: javaDemo->SimplePriorities
 * @description: 线程优先级
 * @author: lanwenquan
 * @date: 2020-05-28 22:19
 */
public class SimplePriorities implements Runnable {
    private int countDown = 5;
    private volatile double d;
    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            // 只是为了有优先级的效果
            for (int i = 0; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double)i;
                if (i % 1000 == 0)
                    Thread.yield();
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }

    @Override
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5;  i++)
            executorService.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        executorService.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        executorService.shutdown();
    }
}
