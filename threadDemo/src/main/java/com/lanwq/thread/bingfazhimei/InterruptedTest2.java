package com.lanwq.thread.bingfazhimei;

/**
 * @author Vin lan
 * @className InterruptedTest2
 * @description
 * @createTime 2021-10-28  11:54
 **/
public class InterruptedTest2 {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            try {
                System.out.println("thread one begin sleep for 2000 seconds");
                Thread.sleep(2000000);
                System.out.println("thread one awaking");
            } catch (InterruptedException e) {
                System.out.println("thread one is interrupted while sleeping");
                return;
            }
            System.out.println("thread one leaving normally");
        });

        threadOne.start();
        // 确保子线程进入休眠状态
        Thread.sleep(1000);
        // 打算子线程的休眠状态，让子线程从 sleep 函数返回
        threadOne.interrupt();
        // 等待子线程自行完毕
        threadOne.join();
        System.out.println("main thread is over");
    }
}
