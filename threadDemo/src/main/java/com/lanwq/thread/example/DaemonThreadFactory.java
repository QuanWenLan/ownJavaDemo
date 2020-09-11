package com.lanwq.thread.example;

import java.util.concurrent.ThreadFactory;

/**
 * @ClassName DaemonThreadFactory
 * @Description 使用 *ThreadLocal*来创建后台线程
 * @Author lanwenquan
 * @Date 2020/05/29 15:18
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
