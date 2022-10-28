package com.lanwq.thread.example.delayQueueDemo;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Vin lan
 * @className Test
 * @description
 * @createTime 2022-10-24  15:15
 **/
public class Test {

    public static void main(String[] args) {
        DelayQueue<TimedTaskDelayedQueue> timedTaskDelayedQueues = new DelayQueue<>();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        TimedTaskDelayedQueue ele1 = new TimedTaskDelayedQueue("1", System.currentTimeMillis(), 10000);
        timedTaskDelayedQueues.add(ele1);
        TimedTaskDelayedQueue ele2 = new TimedTaskDelayedQueue("2", System.currentTimeMillis(), -10000);
        timedTaskDelayedQueues.add(ele2);

        new Thread(()->{
            while (true) {
                if (!timedTaskDelayedQueues.isEmpty()) {
                    TimedTaskDelayedQueue ele = timedTaskDelayedQueues.poll();
                    if (ele != null) {
                        System.out.println(Thread.currentThread().getName() + "\t 一个元素到期了" + ele.getPreId());
                        System.out.println("长度"+ timedTaskDelayedQueues.size());
                    }
                }
            }
        }).start();
    }
}
