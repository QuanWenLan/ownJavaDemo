package com.lanwq.thinkinginjavademo.thread.example;

/**
 * @ClassName MoreThread
 * @Description
 * @Author lanwenquan
 * @Date 2020/05/28 15:34
 */
public class MoreThread {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new LiftOff());
            thread.start(); // 为该线程执行必须的初始化操作
        }
        System.out.println("waiting for liftoff");
    }
}
