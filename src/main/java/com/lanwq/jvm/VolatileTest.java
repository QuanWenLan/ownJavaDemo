package com.lanwq.jvm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Vin lan
 * @className VolatileTest
 * @description TODO
 * @createTime 2021-03-09  14:55
 **/
public class VolatileTest {
    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    private static final int THREAD_COUNT = 20;
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                    count.incrementAndGet();
                }
            });
            threads[i].start();
        }

       /* // 等待所有累加线程结束
        while (Thread.activeCount() > 1)
            Thread.yield();
        System.out.println(race);*/

        while (true) {
            if(count.get() > 19)  {
                System.out.println(count);
                System.out.println(race);
                break;
            }
        }
    }
}
