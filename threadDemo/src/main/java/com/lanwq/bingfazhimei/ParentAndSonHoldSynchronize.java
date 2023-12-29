package com.lanwq.bingfazhimei;

/**
 * @author Lan
 * @createTime 2023-12-29  15:47
 **/
public class ParentAndSonHoldSynchronize {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        // 父线程
        synchronized (lock) {
            System.out.println("Parent Thread: Holding lock");

            // 创建并启动子线程
            Thread childThread = new Thread(() -> {
                synchronized (lock) {
                    // 子线程可以获取到锁，因为它们使用的是相同的锁对象
                    System.out.println("Child Thread: Holding lock");
                }
            });
            childThread.start();

            // 父线程持有锁，不释放
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
