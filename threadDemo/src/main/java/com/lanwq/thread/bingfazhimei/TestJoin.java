package com.lanwq.thread.bingfazhimei;

/**
 * @author Lan
 * @createTime 2023-12-01  21:33
 * 测试thread.join方法
 **/
public class TestJoin {

    public static void main(String args[]) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
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
