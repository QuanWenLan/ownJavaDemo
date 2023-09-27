package com.lanwq.thinkinginjavademo.thread.example;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Daemons
 * @Description daemon线程被设置成了后台线程，然后派生出很多子线程，这些线程并没有被显示的设置成后台模式，但是他们的确是后台线程
 * @Author lanwenquan
 * @Date 2020/05/29 15:35
 */
public class Daemons {
    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.println("d.isDaemon()= " + d.isDaemon() + "");
        // 允许所有的线程完成他们的初始化操作,设置当前主线程睡一会
        TimeUnit.MILLISECONDS.sleep(1);
    }
}

class Daemon implements Runnable {
    private Thread[] t = new Thread[10];

    @Override
    public void run() {
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(new DaemonSpawn());
            t[i].start();
            System.out.println("DaemonSpawn " + i + " started.");
        }
        for (int i = 0; i < t.length; i++) {
            System.out.println("t[" + i + "].isDaemon() = " + t[i].isDaemon() + ",");
        }
        while (true) {
            Thread.yield();
        }
    }
}

class DaemonSpawn implements Runnable {
    @Override
    public void run() {
        while (true) {
            Thread.yield();
        }
    }
}