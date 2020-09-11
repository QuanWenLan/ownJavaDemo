package com.lanwq.thread.example;

/**
 * @ClassName BasicThread
 * @Description
 * @Author lanwenquan
 * @Date 2020/05/28 15:30
 */
public class BasicThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new LiftOff());
        thread.start(); // 为该线程执行必须的初始化操作
        System.out.println("waiting for liftoff");
    }
}
