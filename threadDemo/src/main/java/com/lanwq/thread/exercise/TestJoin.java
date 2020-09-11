package com.lanwq.thread.exercise;

/**
 * @program: javaDemo->TestJoin
 * @description: 测试thread.join方法
 * @author: lanwenquan
 * @date: 2020-08-19 17:18
 */
public class TestJoin {
    public static void main(String args[])throws InterruptedException{
        Thread t=new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("2");
            }
        });
        t.start();

        // join方法 Waits for this thread to die.将t线程加入到主线程main中，主线程等待t线程执行完成再执行后面的代码。
        t.join();
        System.out.print("1");
    }
}
