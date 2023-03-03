package com.lanwq.thread.bingfabianchengyishu.five_lock;

import com.lanwq.thread.bingfabianchengyishu.SleepUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Vin lan
 * @className TwinsLock
 * @description 该工具在同一时刻，只允许至多两个线程同时访问，超过两个线程的
 * 访问将被阻塞，我们将这个同步工具命名为TwinsLock。
 * @createTime 2023-02-27  11:57
 * 1 确定访问模式， 同一时刻支持多个线程的访问，这显然是共享式访问，因此要实现相关的 shared 方法
 * 2 定义资源数，TwinsLock在同一时刻允许至多两个线程的同时访问，表明同步资源数为2，
 * 这样可以设置初始状态status为2，当一个线程进行获取，status减1，该线程释放，则
 * status加1，状态的合法范围为0、1和2，其中0表示当前已经有两个线程获取了同步资源，此时
 * 再有其他线程对同步状态进行获取，该线程只能被阻塞。在同步状态变更时，需要使用
 * compareAndSet(int expect,int update)方法做原子性保障。
 * 3 组合自定义同步器
 **/
public class TwinsLock implements Lock {
    private final Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer {
        public Sync(int count) {
            if (count < 0) {
                throw new IllegalArgumentException("count must large than zero.");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            for (; ; ) {
                int count = getState();
                int newCount = count - reduceCount;
                if (newCount < 0 || compareAndSetState(count, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for (; ; ) {
                int count = getState();
                int newCount = count + returnCount;
                if (compareAndSetState(count, newCount)) {
                    return true;
                }
            }
        }

        // 返回一个Condition，每个condition都包含了一个condition队列
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public static void main(String[] args) {
        String a = "123 43 89";
        System.out.println(a.replaceAll(" ", ""));
        /*final Lock lock = new TwinsLock();
        class Worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        // 每隔1秒换行
        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println();
        }*/
    }
}
