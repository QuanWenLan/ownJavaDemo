package com.lanwq.thread.example;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SimpleDaemons
 * @Description 后台线程, 程序运行的时候在后台提供的一种通用服务的线程，并且这种线程并不属于程序中不可缺少的部分。
 * 只要有任何的后台线程还在运行，程序就不会终止。当所有的非后台线程结束时，程序也就终止了，同时会杀死进程中的所有后台线程
 * 执行的 <code>main()</code>方法的线程就一个非后台线程
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
            daemon.setDaemon(true); // 必须在程序运行之前调用setDaemon(true)方法，才能把它设置为后台线程
            daemon.start();
        }
        System.out.println("all daemons started");
        TimeUnit.MILLISECONDS.sleep(175);
//        TimeUnit.MILLISECONDS.sleep(100);
    }
}
