package com.lanwq.thread.bingfazhimei;

/**
 * @author Vin lan
 * @className YieldTest
 * @description
 * @createTime 2021-10-28  10:53
 **/
public class YieldTest implements Runnable {
    YieldTest() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            // 当 i = 0 时，让出 cpu 执行权，放弃时间片，进行下一轮调度
            if ((i % 5 == 0)) {
                System.out.println(Thread.currentThread() + "yield cpu...");
                // 让出 cpu 执行权，放弃时间片，进行下一轮调度
                 Thread.yield();
            }
        }
        System.out.println(Thread.currentThread() + " is over");
    }

    public static void main(String[] args) {
        new YieldTest();
        new YieldTest();
        new YieldTest();

    }
}
