package com.lanwq.thread.bingfazhimei;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Lan
 * @createTime 2024-01-29  09:55
 **/
public class InheritableThreadLocalTest {
    public static void main(String[] args) {
        test1();
        // 线程池中使用
        System.out.println("==============");
//        fun1();
    }

    static InheritableThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

    private static void test1() {
        threadLocal.set(10); // 初始值
        System.out.println("父线程获取数据-初始值：" + threadLocal.get()); // 输出10
        new Thread(() -> {
            System.out.println("子线程获取数据：" + threadLocal.get()); // 输出10
            threadLocal.set(20); // 子线程中修改值
            System.out.println("子线程获取数据：" + threadLocal.get()); // 输出20
        }).start();
        threadLocal.set(30); // 父线程中修改值
        System.out.println("父线程获取数据：" + threadLocal.get()); // 输出30
        // 子线程中的值，只是在创建的时候会从父线程中复制父线程的值，后续父线程改变了，子线程是不会感知到改变的
    }

    private static void fun1() {
        InheritableThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set(6);
        System.out.println("父线程获取数据：" + threadLocal.get());

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        threadLocal.set(6);
        executorService.submit(() -> {
            System.out.println("第一次从线程池中获取数据：" + threadLocal.get());
        });

        threadLocal.set(7);
        executorService.submit(() -> {
            System.out.println("第二次从线程池中获取数据：" + threadLocal.get());
        });
    }
}
