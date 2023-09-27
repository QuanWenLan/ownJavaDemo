package com.lanwq.jvm.objectheader;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author Vin lan
 * @className ObjectTest1
 * @description https://cloud.tencent.com/developer/article/1863050 , https://www.cnblogs.com/hongdada/p/14087177.html
 * @createTime 2022-01-13  10:07
 * 这个比较有参考意义：https://www.cnblogs.com/LemonFive/p/11246086.html
 **/
public class ObjectTest1 {
    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        test3();
        test4();
    }

    // 无锁状态
    public static void test1() {
        System.out.println("无锁状态。。。");
        Object o = new Object();
        System.out.println(("未进入同步块，MarkWord 为："));
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println("无锁状态，打印结束\n");
    }

    // 偏向锁
    public static void test2() throws InterruptedException {
        System.out.println("偏向锁状态。。。");
        Thread.sleep(5000);
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println("偏向锁状态，打印结束\n");
    }

    // 偏向锁，偏向主线程
    public static void test3() throws InterruptedException {
        System.out.println("偏向锁状态，偏向主线程。。。");
        Thread.sleep(5000);
        Object o = new Object();
        System.out.println(("未进入同步块，睡了5s，MarkWord 为："));
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o){
            // 这个变成了 轻量级锁
            System.out.println(("进入同步块，MarkWord 为："));
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        System.out.println("偏向锁状态，偏向主线程，打印结束\n");
    }

    // 轻量级锁
    public static void test4() throws InterruptedException {
        System.out.println("轻量级锁状态。。。");
        Thread.sleep(5000);
        Object o = new Object();
        Thread thread1= new Thread(){
            @Override
            public void run() {
                synchronized (o){
                    System.out.println("thread1 locking");
                    System.out.println(ClassLayout.parseInstance(o).toPrintable()); //偏向锁
                }
            }
        };
        thread1.start();
        thread1.join();
        Thread.sleep(10000);

        synchronized (o){
            System.out.println("main locking");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());//轻量锁
        }
        System.out.println("轻量级锁状态，打印结束\n");
    }
}
