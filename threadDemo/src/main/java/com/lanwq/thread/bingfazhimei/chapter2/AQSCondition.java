package com.lanwq.thread.bingfazhimei.chapter2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Vin lan
 * @className AQSCondition
 * @description
 * @createTime 2021-11-02  15:10
 **/
public class AQSCondition {

    public static void main(String[] args) {
        // （1）创建一个独占锁，ReentrantLock 是基于 AQS 实现的锁
        ReentrantLock lock = new ReentrantLock();
        // （2）创建了一个 ConditionObject 变量，这个变量就是 lock 锁对应的一个条件变量，
        // 一个lock 对象可以创建多个条件变量
        Condition condition = lock.newCondition();
        // （3）获取独占锁
        lock.lock();
        try {
            System.out.println("begin wait");
            // （4）阻塞挂起当前线程。其他线程调用条件变量的 signal 方法时，被阻塞的线程才会从 await 处返回
//            需要注意的是，和调用Object的wait方法一样，如果在没有获取到锁前调用了条件变量的await方法则会抛出java.lang.IllegalMonitorStateException异常
            condition.await();
            System.out.println("end wait");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // （5）释放获取的锁
            lock.unlock();
        }

        // （6）
        lock.lock();
        try {
            System.out.println("begin signal");
            // （7）
            condition.signal();
            System.out.println("end signal");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // （8）
            lock.unlock();
        }
    }
}
