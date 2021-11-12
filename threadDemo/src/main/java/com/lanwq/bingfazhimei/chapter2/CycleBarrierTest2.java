package com.lanwq.bingfazhimei.chapter2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Vin lan
 * @className CycleBarrierTest1
 * @description
 * @createTime 2021-11-09  17:30
 **/
public class CycleBarrierTest2 {
    /**
     * 创建一个 CycleBarrier 实例，添加一个所有子线程全部到达屏障后执行的任务
     */
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
            try {
                System.out.println(Thread.currentThread() + " step1");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " step2");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " step3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        executorService.submit(()->{
            try {
                System.out.println(Thread.currentThread() + " step1");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " step2");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " step3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }
}
