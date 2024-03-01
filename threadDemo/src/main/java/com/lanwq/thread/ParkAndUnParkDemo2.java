package com.lanwq.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Lan
 * @createTime 2023-06-28  16:46
 **/
public class ParkAndUnParkDemo2 {
    static class MyThread extends Thread {
        private Object object;

        public MyThread(Object object) {
            this.object = object;
        }

        public void run() {
            System.out.println("before unpark");
            // 释放许可
            LockSupport.unpark((Thread) object);
            System.out.println("after unpark");
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread(Thread.currentThread());
        myThread.start();
        try {
            // 主线程睡眠3s
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("before park");
        // 获取许可
        LockSupport.park("ParkAndUnparkDemo");
        System.out.println("after park");
    }
}