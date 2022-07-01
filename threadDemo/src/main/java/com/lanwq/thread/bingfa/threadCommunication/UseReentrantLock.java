package com.lanwq.thread.bingfa.threadCommunication;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Vin lan
 * @className UseReentrantLock
 * @description 使用 ReentrantLock 结合 Condition
 * @createTime 2022-07-01  16:03
 **/
public class UseReentrantLock {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        ArrayList<Object> list = new ArrayList<>();

        //线程A
        Thread threadA = new Thread(() -> {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                list.add("abc");
                System.out.println("线程A添加元素，此时list的size为：" + list.size());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (list.size() == 5) {
                    System.out.println("threadA list = " + list);
                    condition.signal();
                }
            }
            lock.unlock();
        });

        //线程A
        Thread threadB = new Thread(() -> {
            lock.lock();
            if (list.size() != 5) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程B收到通知，此时list的size为：" + list.size() + ", 开始执行自己的业务...");
            list.add("bbc");
            System.out.println("threadB list = " + list);
            lock.unlock();
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
