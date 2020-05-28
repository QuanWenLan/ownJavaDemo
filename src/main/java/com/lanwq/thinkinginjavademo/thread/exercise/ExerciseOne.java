package com.lanwq.thinkinginjavademo.thread.exercise;

/**
 * @ClassName ExerciseOne
 * @Description
 * @Author lanwenquan
 * @Date 2020/05/28 15:44
 */
public class ExerciseOne {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new OneRunnable());
            thread.start(); // 为该线程执行必须的初始化操作
        }
        System.out.println("end for OneRunnable");
    }
}
