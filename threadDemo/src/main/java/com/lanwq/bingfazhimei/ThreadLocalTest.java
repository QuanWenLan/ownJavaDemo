package com.lanwq.bingfazhimei;

/**
 * @author Vin lan
 * @className ThreadLocalTest
 * @description
 * @createTime 2021-10-28  16:42
 **/
public class ThreadLocalTest {
    /**
     * （1） print 函数
     */
    static void print(String str) {
        // 1.1 打印当前线程本地内存中 localVariable 变量的值
        System.out.println(str + " : " + localVariable.get());
        // 1.2 清楚当前线程本地内存中的 localVariable 变量
        localVariable.remove();
    }

    /**
     * （2）创建 ThreadLocal 变量
     */
    static ThreadLocal<String> localVariable = new ThreadLocal<>();
    static ThreadLocal<String> localVariable2 = new ThreadLocal<>();
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        /**
         * （3）创建线程
         */
        Thread threadOne = new Thread(() -> {
            localVariable.set("thread one local variable");
            localVariable2.set("thread one local variable22222222");
            print("thread one");
            System.out.println("thread one remove after " + " : " + localVariable.get());
        });

        Thread threadTwo = new Thread(() -> {
            localVariable.set("thread two local variable");
            print("thread two");
            System.out.println("thread two remove after " + " : " + localVariable.get());
        });

//        threadOne.start();
//        threadTwo.start();
        threadLocal.set("hello world");
        Thread threadThree = new Thread(() -> {

            System.out.println("thread three " + " : " + threadLocal.get());
        });
        threadThree.start();
        System.out.println("main: " + threadLocal.get());
    }
}
