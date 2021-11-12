package com.lanwq.bingfazhimei.chapter2;

import java.util.concurrent.CountDownLatch;

/**
 * @author Vin lan
 * @className JoinCountDownLatch
 * @description CountDownLatch 代码使用
 * @createTime 2021-11-09  16:40
 **/
public class JoinCountDownLatch2 {

    /**
     * 创建一个 CountDownLatch 实例
     */
    private static volatile CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException{
        Thread threadOne = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
            System.out.println("child threadOne over!");
        });

        Thread threadTwo = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
            System.out.println("child threadTwo over!");
        });

        // 启动子线程
        threadOne.start();
        threadTwo.start();
        System.out.println("wait all child thread over!");
//        等待子线程执行完毕，返回
        countDownLatch.await();
        System.out.println("all child thread over!");
    }
}
