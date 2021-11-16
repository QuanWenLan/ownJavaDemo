package com.lanwq.bingfazhimei;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Vin lan
 * @className SleepTest
 * @description
 * @createTime 2021-10-28  10:23
 **/
public class SleepTest {
    // 创建一个独占锁
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            // 获取独占锁
            lock.lock();
            try {
                System.out.println("child thread one is in sleep");
                Thread.sleep(10000);
                System.out.println("child thread one is in awake");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
            }
        });

        Thread threadTwo = new Thread(() -> {
            // 获取独占锁
            lock.lock();
            try {
                System.out.println("child thread two is in sleep");
                Thread.sleep(10000);
                System.out.println("child thread two is in awake");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
            }
        });

        // 启动线程
        threadOne.start();
//        threadTwo.start();
        // 主线程休眠 2s
        Thread.sleep(2000);
        // 主线程中断子线程
        threadOne.interrupt();
    }
}
