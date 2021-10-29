package com.lanwq.bingfazhimei;

/**
 * @author Vin lan
 * @className InterruptedTest2
 * @description interrupted() 和 isInterrupted() 方法的区别
 * @createTime 2021-10-28  11:54
 **/
public class InterruptedTest3 {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            for(;;){}
        });

        threadOne.start();
        // 设置中断标志
        threadOne.interrupt();
        // 获取中断标志
        System.out.println("获取中断标志 isInterrupted:" + threadOne.isInterrupted());
        // 获取中断标志并重置
        System.out.println("获取中断标志并重置 isInterrupted:" + Thread.interrupted());
        // 获取中断标志
        System.out.println("获取中断标志 isInterrupted:" + threadOne.isInterrupted());
        // 等待子线程自行完毕
        threadOne.join();
        System.out.println("main thread is over");
    }
}
