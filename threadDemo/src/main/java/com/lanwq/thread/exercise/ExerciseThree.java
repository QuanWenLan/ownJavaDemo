package com.lanwq.thread.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ExerciseOne
 * @Description
 * @Author lanwenquan
 * @Date 2020/05/28 15:44
 */
public class ExerciseThree {
    public static void main(String[] args) {
        /*ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            cachedThreadPool.execute(new OneRunnable());
        }
        cachedThreadPool.shutdown();*/

        /*ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            fixedThreadPool.execute(new OneRunnable());
        }
        fixedThreadPool.shutdown();*/

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            singleThreadExecutor.execute(new OneRunnable());
        }
        singleThreadExecutor.shutdown();
    }
}

