package com.lanwq.thread.bingfazhimei.chapter2;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;

/**
 * @author Vin lan
 * @className NonReentrantLockTest
 * @description 生产者消费者，使用自定义的锁实现
 * @createTime 2021-11-02  16:08
 **/
public class NonReentrantLockTest {
    final static NonReentrantLock LOCK = new NonReentrantLock();
    final static Condition NOT_FULL = LOCK.newCondition();
    final static Condition NOT_EMPTY = LOCK.newCondition();
    final static Queue<String> QUEUE = new LinkedBlockingDeque<String>();
    final static int QUEUE_SIZE = 10;

    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(() -> {
            // 获取独占锁
            LOCK.lock();
            try {
//                （1）如果队列满了，则等待
                while (QUEUE.size() == QUEUE_SIZE) {
                    NOT_EMPTY.await();
                }
//                （2）添加元素到队列
                QUEUE.add("ele");
                System.out.println("添加元素了，队列大小为：" + QUEUE.size());
//                （3）唤醒消费线程
                NOT_FULL.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
//                释放锁
                LOCK.unlock();
            }
        });

        Thread consumer = new Thread(() -> {
            // 获取独占锁
            LOCK.lock();
            try {
//                （1）如果队列空了，则等待
                while (QUEUE.size() == 0) {
                    System.out.println("queue 大小为 0");
                    NOT_FULL.await();
                }
                System.out.println("开始获取其中一个元素");
//                （2）消费一个元素
                String ele = QUEUE.poll();
                System.out.println("消费的元素：" + ele);
//                （3）唤醒生产线程
                NOT_EMPTY.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
//                释放锁
                LOCK.unlock();
            }
        });

        producer.start();
        consumer.start();
    }
}
