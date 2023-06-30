package com.lanwq;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 验证公平锁和非公平锁源码
 *
 * @author Lan
 * @createTime 2023-06-28  15:16
 **/


public class AbstractQueuedSynchronizerDemo {
    static class MyThread extends Thread {
        private Lock lock;

        public MyThread(String name, Lock lock) {
            super(name);
            this.lock = lock;
        }

        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread() + " running");
                try {
                    Thread.sleep(5 * 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        Lock lock = new ReentrantLock(true);
        Lock lock = new ReentrantLock();

        for (int i = 1; i <= 10; i++) {
            MyThread t = new MyThread("线程 t" + i, lock);
            t.start();
            Thread.sleep(100);
        }
    }
}
