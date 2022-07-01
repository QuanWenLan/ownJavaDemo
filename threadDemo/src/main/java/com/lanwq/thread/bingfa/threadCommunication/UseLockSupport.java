package com.lanwq.thread.bingfa.threadCommunication;

import java.util.ArrayList;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Vin lan
 * @className UseLockSupport
 * @description 基本 LockSupport 实现线程间的阻塞和唤醒
 * @createTime 2022-07-01  16:11
 **/
public class UseLockSupport {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        //线程B
        final Thread threadB = new Thread(() -> {
            if (list.size() != 5) {
                LockSupport.park();
            }
            System.out.println("线程B收到通知，此时的list的长度为：" + list.size() + ",开始执行自己的业务...");
        });
        //线程A
        Thread threadA = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                list.add("abc");
                System.out.println("线程A添加元素，此时list的size为：" + list.size());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (list.size() == 5) {
                    System.out.println("唤醒B线程，此时 list 的长度是:" + list.size());
                    LockSupport.unpark(threadB);
                }
            }
        });
        threadA.start();
        threadB.start();
    }
}
