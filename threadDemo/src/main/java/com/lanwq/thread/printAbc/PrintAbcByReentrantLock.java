package com.lanwq.thread.printAbc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Vin lan
 * @className PrintAbc
 * @description 3 个线程交替打印 ABC 10次，使用 reentrant lock
 * @createTime 2023-03-31  16:18
 **/
public class PrintAbcByReentrantLock {
    /**
     * 定义一个变量控制
     */
    private static AtomicInteger state = new AtomicInteger(1);
    private static Lock lock = new ReentrantLock();
    private static Condition A = lock.newCondition();
    private static Condition B = lock.newCondition();
    private static Condition C = lock.newCondition();

    /**
     * 定义三个条件
     *
     * @param args
     */

    public static void main(String[] args) {
        new Thread(() -> {
            for (int j = 1; j <= 10; j++) {
                printA();
            }
        }, "A线程").start();
        new Thread(() -> {
            for (int j = 1; j <= 10; j++) {
                printB();
            }
        }, "B线程").start();
        new Thread(() -> {
            for (int j = 1; j <= 10; j++) {
                printC();
            }
        }, "C线程").start();
    }

    private static void printA() {
        // 线程都去获取锁，获取不到加入到 AQS 的队列中
        lock.lock();
        try {
            while (state.get() % 3 != 1) {
                // 当前线程会释放自己获取到的锁，并将线程加入到condition条件队列中
                A.await();
            }
//            System.out.println(Thread.currentThread().getName() + "A");
            System.out.print("A");
            state.getAndIncrement();
            B.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void printB() {
        lock.lock();
        try {
            while (state.get() % 3 != 2) {
                // 当前线程当前线程会释放自己获取到的锁，并将线程加入到condition条件队列中
                B.await();
            }
//            System.out.println(Thread.currentThread().getName() + "B");
            System.out.print("B");
            state.getAndIncrement();
            C.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void printC() {
        lock.lock();
        try {
            while (state.get() % 3 != 0) {
                // 当前线程会释放自己获取到的锁，并将线程加入到condition条件队列中
                C.await();
            }
//            System.out.println(Thread.currentThread().getName() + "C");
            System.out.print("C");
            state.getAndIncrement();
            A.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
