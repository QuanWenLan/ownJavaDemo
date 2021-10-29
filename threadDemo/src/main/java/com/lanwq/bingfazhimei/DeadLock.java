package com.lanwq.bingfazhimei;

/**
 * @author Vin lan
 * @className DeadLock
 * @description 死锁
 * @createTime 2021-10-28  12:16
 **/
public class DeadLock {
    //    创建资源
    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        //  创建线程
        Thread threadA = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println(Thread.currentThread() + "get resourceA");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " waiting get resourceB");
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + "get resourceB");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (resourceB) {
                System.out.println(Thread.currentThread() + "get resourceB");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " waiting get resourceA");
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + "get resourceA");
                }
            }
        });

        Thread threadB2 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println(Thread.currentThread() + "get resourceB");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " waiting get resourceB");
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + "get resourceB");
                }
            }
        });

        threadA.start();
//        threadB.start();
        threadB2.start();
    }
}
