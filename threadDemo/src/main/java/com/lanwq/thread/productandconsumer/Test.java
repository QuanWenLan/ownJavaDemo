package com.lanwq.thread.productandconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Test
 * @Description
 * @Author lanwenquan
 * @Date 2020/06/12 15:49
 */
public class Test {
    public static void main(String[] args) {
        LinkedBlockingQueue<Data> resultQueue = new LinkedBlockingQueue<>();

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(new Producer(new AtomicInteger(10), resultQueue));

        executorService.submit(new Consumer(resultQueue));

        executorService.shutdown();
    }
}
