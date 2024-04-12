package com.lanwq.thread.printAbc;

import java.util.concurrent.Semaphore;

/**
 * @author Vin lan
 * @className PrintAbcBySemaphore
 * @description 通过信号量控制打印顺序
 * https://blog.csdn.net/hefenglian/article/details/82596072 参考博客
 * @createTime 2023-03-31  17:07
 **/
public class PrintAbcBySemaphore {
    /**
     * Semaphore内部主要通过AQS（AbstractQueuedSynchronizer）实现线程的管理。Semaphore有两个构造函数，第一个参数permits表示许可数，
     * 它最后传递给了AQS的state值。线程在运行时首先获取许可，如果成功，许可数就减1，线程运行，当线程运行结束就释放许可，许可数就加1。
     * 如果许可数为0，则获取失败，线程位于AQS的等待队列中，它会被其它释放许可的线程唤醒。在创建Semaphore对象的时候还可以指定它的公平性。
     * 一般常用非公平的信号量，非公平信号量是指在获取许可时先尝试获取许可，而不必关心是否已有需要获取许可的线程位于等待队列中，如果获取失败，才会入列。
     * 而公平的信号量在获取许可时首先要查看等待队列中是否已有线程，如果有则入列。
     */
    /**
     * A 默认可以先获取到
     */
    private static Semaphore semaphoreA = new Semaphore(1);
    /**
     * B 信号量的线程，默认是获取不到的会先到AQS队列中
     */
    private static Semaphore semaphoreB = new Semaphore(0);
    private static Semaphore semaphoreC = new Semaphore(0);

    public static void main(String[] args) {
        new Thread(() -> {
            for (int j = 1; j <= 10; j++) {
                printA();
            }
        }, "A线程").start();
        new Thread(() -> {
            for (int j = 1; j <= 10; j++) {
                printB();
            }
        }, "B线程").start();
        new Thread(() -> {
            for (int j = 1; j <= 10; j++) {
                printC();
            }
        }, "C线程").start();
    }

    private static void printA() {
        try {
            semaphoreA.acquire();
            System.out.print("A");
            semaphoreB.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printB() {
        try {
            semaphoreB.acquire();
            System.out.print("B");
            semaphoreC.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printC() {
        try {
            semaphoreC.acquire();
            System.out.print("C");
            semaphoreA.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
