package com.lanwq.thread.bingfazhimei;

/**
 * @author Vin lan
 * @className JoinTest
 * @description join() 方法测试
 * @createTime 2021-10-28  09:57
 **/
public class JoinTest2 {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("child threadOne over");
        });

        Thread threadTwo = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("child threadTwo over");
        });

        threadOne.start();
        threadTwo.start();
        System.out.println("wait all child thread over");
        threadOne.join();
        threadTwo.join();
    }
}
