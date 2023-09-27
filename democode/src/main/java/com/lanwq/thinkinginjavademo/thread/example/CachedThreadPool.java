package com.lanwq.thinkinginjavademo.thread.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CachedThreadPool
 * @Description CachedThreadPool将为每一个任务都创建一个线程，通常会创建所需数量相同的线程，然后在它回收 旧线程 时停止创建新线程
 * @Author lanwenquan
 * @Date 2020/05/28 16:03
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            // 方法解释：
            pool.execute(new LiftOff());
        }
        // 执行完毕之后要关闭线程池，防止新任务被提交给Executor
        pool.shutdown();

        // "=======使用newFixedThreadPool======"
        /**
         * 使用有限的线程集来执行所提交的任务。可以预先执行代价高昂的线程分配。因此可以限制线程的数量。
         */
        ExecutorService pool2 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            // 方法解释：
            pool2.execute(new LiftOff());
        }
        // 执行完毕之后要关闭线程池，防止新任务被提交给Executor
        pool2.shutdown();

    }
}
