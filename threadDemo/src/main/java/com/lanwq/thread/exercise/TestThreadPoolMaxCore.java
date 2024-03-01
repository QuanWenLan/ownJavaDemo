package com.lanwq.thread.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Vin lan
 * @className T1
 * @description 验证从core扩容到maximum后，立即运行当前到达的任务，而不是队列中的
 * @createTime 2023-03-23  17:29
 **/
public class TestThreadPoolMaxCore {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                100,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        try {
            for (int i = 1; i <= 8; i++) {
                final int tempInt = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "号窗口，服务顾客" + tempInt);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
    /*
     * 运行结果：
     * core=2，所以1号窗口对应1号顾客，2号窗口对应2号顾客，但是接下来，3、4、5号顾客又来了，进入容量为3的队列中排队，接下来6、7、8号顾客又来了，
     * 1、2号窗口正在服务，且队列也满了，此时应该开启3、4、5号窗口来提供服务，为6、7、8号顾客提供服务，然后再由这5个窗口为3、4、5号顾客提供服务。
     *
     * pool-1-thread-1号窗口，服务顾客1
     * pool-1-thread-3号窗口，服务顾客6
     * pool-1-thread-4号窗口，服务顾客7
     * pool-1-thread-5号窗口，服务顾客8
     * pool-1-thread-2号窗口，服务顾客2
     * 3s后，
     * pool-1-thread-4号窗口，服务顾客3
     * pool-1-thread-5号窗口，服务顾客4
     * pool-1-thread-2号窗口，服务顾客5
     */
}
