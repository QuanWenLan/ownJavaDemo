package com.lanwq.jvm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: javaDemo->AtomicTest
 * @description: 第12章中有写到的20个县城自增10000次，来证明volatile变量不具备原子性
 * @author: lanwenquan
 * @date: 2021-04-28 21:53
 * Atomic 变量自增运算测试
 */
public class AtomicTest {
    // 元子类的操作是属于 乐观锁
    public static AtomicInteger race = new AtomicInteger(0);

    public static void increase() {
        race.incrementAndGet();
    }

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println(race);
    }
}
