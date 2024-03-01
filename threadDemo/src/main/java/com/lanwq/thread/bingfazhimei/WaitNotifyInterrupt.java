package com.lanwq.thread.bingfazhimei;

/**
 * @author Vin lan
 * @className WaitNotifyInterupt
 * @description
 * @createTime 2021-10-27  17:24
 **/
public class WaitNotifyInterrupt {
    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            try {
                System.out.println("---begin---");
                synchronized (obj) {
                    System.out.println("阻塞之前");
                    obj.wait();
                }
                System.out.println("---end---");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadA.start();
        Thread.sleep(1000);
        System.out.println("---------begin interrupt threadA --------");
//        threadA.interrupt();
        System.out.println("---------end interrupt threadA----------");
        System.out.println("notify begin");
        synchronized (obj) {
            obj.notify();
        }
        System.out.println("notify end");
    }
}
