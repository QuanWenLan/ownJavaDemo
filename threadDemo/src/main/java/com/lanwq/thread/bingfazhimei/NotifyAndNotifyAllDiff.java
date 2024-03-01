package com.lanwq.thread.bingfazhimei;

/**
 * @author Vin lan
 * @className NotifyAndNotifyAllDiff
 * @description notify() 和 notifyAll() 方法的区别，需要注意的地方
 * @createTime 2021-10-28  09:23
 **/
public class NotifyAndNotifyAllDiff {
    private static volatile Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
           // 获取 resourceA 共享资源的监视器锁
           synchronized (resourceA) {
               System.out.println("threadA get resourceA lock");
               try {
                   System.out.println("threadA begin wait");
                   resourceA.wait();
                   System.out.println("threadA end wait");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        Thread threadB = new Thread(() -> {
            // 获取 resourceA 共享资源的监视器锁
            synchronized (resourceA) {
                System.out.println("threadB get resourceA lock");
                try {
                    System.out.println("threadB begin wait");
                    resourceA.wait();
                    System.out.println("threadB end wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadC = new Thread(() -> {
            // 获取 resourceA 共享资源的监视器锁
            synchronized (resourceA) {
                System.out.println("threadC begin notify");
//                resourceA.notify();
                resourceA.notifyAll();
            }
        });

        threadA.start();
        threadB.start();
//        Thread.sleep(1000);
        threadC.start();
        // 等待线程结束
        threadA.join();
        threadB.join();
        threadC.join();
        System.out.println("main over");
    }
}
