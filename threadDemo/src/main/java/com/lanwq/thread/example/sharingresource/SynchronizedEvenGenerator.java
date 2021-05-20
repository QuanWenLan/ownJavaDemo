package com.lanwq.thread.example.sharingresource;

/**
 * @author Vin lan
 * @className SynchronizedEvenGenerator
 * @description simplifying mutexes with the synchronized keyword
 * @createTime 2021-03-15  16:36
 **/
public class SynchronizedEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    /**
     * 第一个进入next()的任务将会获得锁，任何其他视图获取锁的任务都将从其开设尝试之时被阻塞，直至第一个任务释放锁。
     */
    @Override
    public synchronized int next() {
        ++currentEvenValue;
        /**
         * 这里的调用是为了提高在currentEvenValue是奇数状态时上下文切换的可能性。因为互斥可以防止多个任务同时进入临界区，
         * 所以这不会产生任何失败。但是如果失败将发生，调用yield()是一种促使其发生的有效方式。
         */
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
