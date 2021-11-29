package com.lanwq.jvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: javaDemo->DeepLoopClass
 * @description: 死循环初始化类
 * @author: lanwenquan
 * @date: 2020-12-27 15:20
 */
public class DeadLoopClass {
    static {
        if (true) {  // if not add if block,it will be show "initializer must be able to complete normally"
            System.out.println(Thread.currentThread() + "init DeadLoopClass");
            /*while (true) {

            }*/
        }
    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            System.out.println(Thread.currentThread() + "start");
            DeadLoopClass deadLoopClass = new DeadLoopClass();
            System.out.println(Thread.currentThread() + "run over");
        });
        executorService.execute(() -> {
            System.out.println(Thread.currentThread() + "start");
            DeadLoopClass deadLoopClass = new DeadLoopClass();
            System.out.println(Thread.currentThread() + "run over");
        });
    }
}
