package com.lanwq.thinkinginjavademo.thread.example;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SimpleDaemons
 * @Description 后台线程, 程序运行的时候在后台提供的一种通用服务的线程，并且这种线程并不属于程序中不可缺少的部分。
 * 只要有任何的后台线程还在运行，程序就不会终止。
 * @Author lanwenquan
 * @Date 2020/05/29 14:55
 */
public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("all daemons started");
//        TimeUnit.MILLISECONDS.sleep(175);
        TimeUnit.MILLISECONDS.sleep(100);
    }
}
