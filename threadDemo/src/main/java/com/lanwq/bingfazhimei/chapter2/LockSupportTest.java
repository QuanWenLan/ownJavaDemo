package com.lanwq.bingfazhimei.chapter2;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Vin lan
 * @className LockSupportTest
 * @description
 * @createTime 2021-11-02  12:07
 **/
public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        /*System.out.println("begin park!");
        // 使当前线程获取到许可证
        LockSupport.unpark(Thread.currentThread());
        // 再次调用 park 方法
        LockSupport.park();
        System.out.println("end park!");*/

        // 另一个例子
        Thread thread = new Thread(() -> {
            System.out.println("child thread begin park!");
            LockSupport.park();
            System.out.println("child thread unpark!");
        });
        // 启动子线程
        thread.start();
        // 主线程休眠 1s
        Thread.sleep(1000);
        System.out.println("main thread begin unpark!");
        // 调用 unpark 方法，让thread线程持有许可证，然后park方法返回
        LockSupport.unpark(thread);


        // 另一个例子
        Thread thread2 = new Thread(() -> {
            System.out.println("child thread begin park!");
            while (!Thread.currentThread().isInterrupted()) {
                LockSupport.park();
            }
            System.out.println("child thread unpark!");
        });
        // 启动子线程
        thread2.start();
        // 主线程休眠 1s
        Thread.sleep(1000);
        System.out.println("main thread begin unpark!");
        // 调用 unpark 方法，让thread线程持有许可证，然后park方法返回
        thread2.interrupt();
    }
}
