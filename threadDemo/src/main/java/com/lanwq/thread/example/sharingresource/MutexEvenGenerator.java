package com.lanwq.thread.example.sharingresource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Vin lan
 * @className MutexEvenGenerator
 * @description preventing thread collisions with mutexes
 * @createTime 2021-03-15  16:36
 **/
public class MutexEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    private Lock lock = new ReentrantLock();
    /**
     * 定义在java.util.concurrent.locks.Lock中的显示的互斥机制。使用显示的Lock对象，Lock对象必须被显示的创建、锁定和释放
     * 第一个进入next()的任务将会获得锁，任何其他视图获取锁的任务都将从其开设尝试之时被阻塞，直至第一个任务释放锁。
     */
    @Override
    public synchronized int next() {
        // 推荐使用这种方式的写法
        lock.lock();
        try {
            ++currentEvenValue;
            /**
             * 这里的调用是为了提高在currentEvenValue是奇数状态时上下文切换的可能性。因为互斥可以防止多个任务同时进入临界区，
             * 所以这不会产生任何失败。但是如果失败将发生，调用yield()是一种促使其发生的有效方式。
             */
            Thread.yield();
            ++currentEvenValue;
            return currentEvenValue;
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }
}
