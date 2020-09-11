package com.lanwq.thread.bingfa;

/**
 * @ClassName ThreadName
 * @Description TODO
 * @Author lanwenquan
 * @Date 2020/5/13 16:32
 */
public class ThreadName {
    public static void main(String[] args) {
        System.out.println("main thread " + Thread.currentThread().getName() + " is running");
        for (int i = 0 ; i < 10; i++) {
            new Thread(i + ""){
                @Override
                public void run() {
                    System.out.println("thread: " + getName() + " is running");
                }
            }.start();
        }
    }
}