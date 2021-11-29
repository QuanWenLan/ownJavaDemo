package com.lanwq.bingfazhimei;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Vin lan
 * @className ThreadLocalPoolTest
 * @description
 * @createTime 2021-11-15  10:39
 **/
public class ThreadLocalPoolTest {
    static class LocalVariable {
        private Long[] a = new Long[1024 * 1024];
    }

    /**
     * （1）
      */
    final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
    /**
     * （2）
      */
    final static ThreadLocal<LocalVariable> LOCAL_VARIABLE_THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            POOL_EXECUTOR.execute(() -> {
//                (4)
                LOCAL_VARIABLE_THREAD_LOCAL.set(new LocalVariable());
//                (5)
                System.out.println("use local variable");
                LOCAL_VARIABLE_THREAD_LOCAL.remove();
            });
            Thread.sleep(1000);
        }
//        (6)
        System.out.println("pool execute over");
    }
}
