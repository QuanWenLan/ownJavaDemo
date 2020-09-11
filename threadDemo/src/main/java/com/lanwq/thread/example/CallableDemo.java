package com.lanwq.thread.example;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @ClassName CallableDemo
 * @Description 使用call接口，执行任务，并返回值
 * @Author lanwenquan
 * @Date 2020/05/28 16:41
 */
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ArrayList<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futures.add(cachedThreadPool.submit(new TaskWithResult(i)));
        }
        for (Future<String> fun : futures) {
            try {
                // get()方法将会阻塞，直到获取到值
                System.out.println(fun.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                cachedThreadPool.shutdown();
            }
        }
    }
}

class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() {
        return "result of taskWithResult " + id;
    }
}