package com.lanwq.thread.bingfabianchengyishu.four_thread;

import com.lanwq.thread.bingfabianchengyishu.SleepUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author Vin lan
 * @className Interruped
 * @description
 * @createTime 2023-02-22  16:41
 **/
public class Interrupted {
    public static void main(String[] args) throws Exception {
        // sleepThread不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);
        // busyThread不停的运行
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        // 休眠5秒，让sleepThread和busyThread充分运行
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
        // 防止sleepThread和busyThread立刻退出SleepUtils.second(2);
        /*
        SleepThread interrupted is false
        BusyThread interrupted is true
        java.lang.InterruptedException: sleep interrupted
	    at java.lang.Thread.sleep(Native Method)
         *从结果可以看出，抛出InterruptedException的线程SleepThread，其中断标识位被清除了，
            而一直忙碌运作的线程BusyThread，中断标识位没有被清除
         */
    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
            }
        }
    }
}
