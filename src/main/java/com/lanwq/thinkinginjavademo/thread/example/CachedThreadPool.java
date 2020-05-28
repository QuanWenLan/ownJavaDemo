package com.lanwq.thinkinginjavademo.thread.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CachedThreadPool
 * @Description
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
        ExecutorService pool2 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            // 方法解释：
            pool2.execute(new LiftOff());
        }
        // 执行完毕之后要关闭线程池，防止新任务被提交给Executor
        pool2.shutdown();

    }
}
