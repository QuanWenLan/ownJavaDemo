package com.lanwq.thinkinginjavademo.thread.exercise;

/**
 * @ClassName OneRunnable
 * @Description
 * @Author lanwenquan
 * @Date 2020/05/28 16:32
 */
public class OneRunnable implements Runnable {
    private static int count = 0;
    private final int ID = count++;

    public OneRunnable() {
        System.out.println("创建了一个(" + ID + ")constructor oneRunnable");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("(" + ID + ")run (" + i + ")...");
            Thread.yield();
        }
    }
}
