package com.lanwq.bingfazhimei;

/**
 * @author Vin lan
 * @className ThreadTest2
 * @description 当前线程持有共享变量的锁和其他共享变量的锁
 * @createTime 2021-10-27  17:11
 **/
public class ThreadTest2 {
    /**
     * 创建资源
     */
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            try {
                // 获取 resourceA 共享资源的监视器锁
                synchronized (resourceA) {
                    System.out.println("threadA get resourceA lock");

                    // 获取 resourceA 共享资源的监视器锁
                    synchronized (resourceB) {
                        System.out.println("ThreadA get resourceB lock");
                        // 线程A 阻塞，并释放获取到的 resourceA 的锁
                        System.out.println("threadA release resourceA lock");
                        resourceA.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                // 休眠 1 秒
                Thread.sleep(1000);
                // 获取 resourceA 共享资源的监视器锁
                synchronized (resourceA) {
                    System.out.println("threadB get resourceA lock");
                    System.out.println("threadB try get resourceB lock...");
                    // 获取 resourceB 共享资源的监视器锁
                    synchronized (resourceB) {
                        System.out.println("ThreadB get resourceB lock");
                        // 线程 B 阻塞，并释放获取到的 resourceA 的锁
                        System.out.println("threadB release resourceA lock");
                        resourceA.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 启动线程
        threadA.start();
        threadB.start();
        // 等待2个线程结束
        threadA.join();
        threadB.join();
        System.out.println("main over");
    }
}
