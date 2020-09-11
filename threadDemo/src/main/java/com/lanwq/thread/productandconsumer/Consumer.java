package com.lanwq.thread.productandconsumer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Consumer 消费者
 * @Description
 * @Author lanwenquan
 * @Date 2020/06/12 13:54
 */
public class Consumer implements Runnable {
    private LinkedBlockingQueue<Data> resultQueue;

    public Consumer(LinkedBlockingQueue<Data> resultQueue) {
        this.resultQueue = resultQueue;
    }

    @Override
    public void run() {
        Data data;
        try {
            while ((data = resultQueue.poll(2, TimeUnit.SECONDS)) != null) {
                // 获取数据, 执行计算操作
                int re = data.getNumber() * 10;
                System.out.println("after cal, value is : " + re);
                TimeUnit.MILLISECONDS.sleep(100L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
