package com.lanwq.thread.productandconsumer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Producer 生产者
 * @Description
 * @Author lanwenquan
 * @Date 2020/06/12 13:54
 */
public class Producer implements Runnable {
    private LinkedBlockingQueue<Data> resultQueue;

    private AtomicInteger count;

    public Producer(AtomicInteger count, LinkedBlockingQueue<Data> resultQueue) {
        this.count = count;
        this.resultQueue = resultQueue;
    }

    @Override
    public void run() {
        try {
            while (count.decrementAndGet() != 0) {
                Data data = new Data(count.get());
                System.out.println(data + " is put into queue");
                if (!resultQueue.offer(data, 2, TimeUnit.SECONDS)) {
                    // 将数据放入队列缓冲区中
                    System.out.println("faild to put data : " + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
