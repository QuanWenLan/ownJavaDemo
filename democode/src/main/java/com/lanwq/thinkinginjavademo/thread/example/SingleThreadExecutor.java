package com.lanwq.thinkinginjavademo.thread.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName SingleThreadExecutor 就像是线程数量为 1 的FixedThreadPool。这对于你希望在另一个线程中连续运行的任何事物
 * （长期存活的任务）
 * @Description 多个任务提交到一个SingleThreadPool里面的时候，会维护自己的悬挂任务队列,
 * 这些任务将会排队，每个任务都会在下一个任务开始之前运行结束，所有的任务将使用相同的线程。（会序列化这些任务）
 * @Author lanwenquan
 * @Date 2020/05/28 16:25
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            singleThreadExecutor.execute(new LiftOff());
        }
        singleThreadExecutor.shutdown();
    }
}
