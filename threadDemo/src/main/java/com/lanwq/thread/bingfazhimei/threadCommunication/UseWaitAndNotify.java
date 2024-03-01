package com.lanwq.thread.bingfazhimei.threadCommunication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vin lan
 * @className UseWaitAndNotify
 * @description 使用 Object 类的 wait()/notify()
 * @createTime 2022-06-23  12:04
 **/
public class UseWaitAndNotify {
    /**
     * 注意：wait/notify 必须配合 synchronized 使用，wait 方法释放锁，notify 方法不释放锁。wait 是指在一个已经进入了同步锁的线程内，
     * 让自己暂时让出同步锁，以便其他正在等待此锁的线程可以得到同步锁并运行，只有其他线程调用了notify()，notify并不释放锁，
     * 只是告诉调用过wait()的线程可以去参与获得锁的竞争了，但不是马上得到锁，因为锁还在别人手里，别人还没释放，
     * 调用 wait() 的一个或多个线程就会解除 wait 状态，重新参与竞争对象锁，程序如果可以再次得到锁，就可以继续向下运行
     *
     *
     * 由输出结果，在线程 A 发出 notify() 唤醒通知之后，依然是走完了自己线程的业务之后，
     * 线程 B 才开始执行，正好说明 notify() 不释放锁，而 wait() 释放锁。
     */

    public static void main(String[] args) {
        // 定义一个锁对象
        Object lock = new Object();
        List<String> list = new ArrayList<>();
        //线程A
        Thread threadA = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    list.add("abc");
                    System.out.println("线程A添加元素，此时list的size为：" + list.size());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (list.size() == 5) {
                        lock.notify(); // 唤醒B线程
                        System.out.println("threadA list = " + list);
                    }
                }
            }
        }, "A");

        //线程A
        Thread threadB = new Thread(() -> {
            synchronized (lock) {
                while (true) {
                    if (list.size() != 5) {
                        try {
                            lock.wait();

                            System.out.println("线程B收到通知，此时的list的长度为：" + list.size() + ",开始执行自己的业务...");
                            System.out.println("添加一个 bbc ");
                            list.add("bbc");
                            System.out.println("threadB list = " + list);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }, "B");
        //需要先启动线程B
        threadB.start();
        try {
            Thread.sleep(1000);
            // 再启动线程A
            threadA.start();
            Thread.sleep(10000);
            System.out.println(threadA.isAlive());
            System.out.println(threadB.isAlive());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
/**
 由输出结果，在线程 A 发出 notify() 唤醒通知之后，依然是走完了自己线程的业务之后，线程 B 才开始执行，
 正好说明 notify() 不释放锁，而 wait() 释放锁。
输出结果：
 线程A添加元素，此时list的size为：1
 线程A添加元素，此时list的size为：2
 线程A添加元素，此时list的size为：3
 线程A添加元素，此时list的size为：4
 线程A添加元素，此时list的size为：5
 threadA list = [abc, abc, abc, abc, abc]
 线程A添加元素，此时list的size为：6
 线程A添加元素，此时list的size为：7
 线程A添加元素，此时list的size为：8
 线程A添加元素，此时list的size为：9
 线程A添加元素，此时list的size为：10
 线程B收到通知，开始执行自己的业务...
 添加一个 bbc
 threadB list = [abc, abc, abc, abc, abc, abc, abc, abc, abc, abc, bbc]
*/
