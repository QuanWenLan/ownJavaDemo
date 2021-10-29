package com.lanwq.bingfazhimei;

/**
 * @author Vin lan
 * @className JoinTest
 * @description join() 方法测试
 * @createTime 2021-10-28  09:57
 **/
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            System.out.println("thread one begin run!");
            for (; ; ) {
            }
        });
        // 获取主线程
        final Thread mainThread = Thread.currentThread();

        Thread threadTwo = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           // 中断主线程
            mainThread.interrupt();
        });
        // 启动子线程
        threadOne.start();
        // 延迟 1 s 启动线程
        threadTwo.start();
        try {
            threadOne.join(); // 等待线程one结束
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("main thread :" + e);
        }
    }
}
