package com.lanwq.thread.bingfazhimei.threadCommunication;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @author Vin lan
 * @className UseCountDownLatch
 * @description
 * @createTime 2022-07-01  15:56
 * 多线程间的5种通信方式
 * https://mp.weixin.qq.com/s/zRXvaR_2j0FSHPMqKACW6A
 * 有两个线程，A 线程向一个集合里面依次添加元素“abc”字符串，一共添加十次，
 * 当添加到第五次的时候，希望 B 线程能够收到 A 线程的通知，然后 B 线程执行相关的业务操作。
 * 线程间通信的模型有两种：共享内存和消息传递
 **/
public class UseCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ArrayList<Object> list = new ArrayList<>();

        //线程A
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                list.add("abc");
                System.out.println("线程A添加元素，此时list的size为：" + list.size());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (list.size() == 5) {
                    countDownLatch.countDown();
                    System.out.println("threadA list = " + list);
                }
            }
        });

        //线程A
        Thread threadB = new Thread(() -> {
//            while (true) {
                if (list.size() != 5) {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程B收到通知，此时list的size为：" + list.size() + ", 开始执行自己的业务...");
                list.add("bbc");
                System.out.println("threadB list = " + list);
//                break;
//            }
        });

        //需要先启动线程B
        threadB.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 再启动线程A
        threadA.start();
    }
}
