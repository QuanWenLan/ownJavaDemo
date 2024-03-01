package com.lanwq.thread.bingfazhimei;

/**
 * @author Vin lan
 * @className InterruptedTest
 * @description 线程中断
 * @createTime 2021-10-28  11:32
 **/
public class InterruptedTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            // 线程退出条件
            while (!Thread.currentThread().isInterrupted()) {
                // do more work
                System.out.println(Thread.currentThread() + " hello");
            }
        });
        thread.start();
        // 主线程休眠 1s，以便中断前让子线程输出
        Thread.sleep(500);
        System.out.println("main thread interrupt thread");
        thread.interrupt();
        // 等待子线程执行完毕
        thread.join();
        System.out.println("main is over");
    }
}
