package com.lanwq.bingfazhimei;

/**
 * @author Vin lan
 * @className DaemonThread
 * @description 守护线程 和 用户线程
 * @createTime 2021-10-28  14:15
 **/
public class DaemonThread {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> {

        });
        daemonThread.setDaemon(true);
        daemonThread.start();

        Thread userThread = new Thread(() -> {
            for (; ; ) {
            }
        });
        userThread.setDaemon(true);
        userThread.start();
        System.out.println("main thread is over");
    }
}
