package com.lanwq.thread.bingfabianchengyishu.four_thread.connectpool;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Vin lan
 * @className ConnectionPoolTest
 * @description 简易连接池测试类
 * @createTime 2023-02-24  14:50
 **/
public class ConnectionPoolTest {
    static ConnectionPool pool = new ConnectionPool(10);
    /**
     * 保证所有ConnectionRunner能够同时开始
     */
    static CountDownLatch start = new CountDownLatch(1);
    /**
     * main线程将会等待所有ConnectionRunner结束后才能继续执行
     */
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        /*
         * 模拟客户端ConnectionRunner获
         * 取、使用、最后释放连接的过程，当它使用时连接将会增加获取到连接的数量，反之，将会增
         * 加未获取到连接的数量
         */
        // 可以修改线程数量进行观察
        int threadCount = 20;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            new Thread(new ConnectionRunner(count, got, notGot)).start();
        }
        start.countDown();
        end.await();
        System.out.println("thread count: " + threadCount);
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection: " + notGot);
        DecimalFormat df = new DecimalFormat("0.0000");
        System.out.println("not got / total invoke : " + df.format((float) notGot.get() / (threadCount * count)));
    }

    static class ConnectionRunner implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (count > 0) {
                try {
                    // 从线程池中获取连接，如果1000ms内无法获取到，将会返回null
                    // 分别统计连接获取的数量got和未获取到的数量notGot
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
    /*
     * 实验结果：
     * 随着客户端线程的逐步增加，客户端出现超时无法获取连接的比率不断升高。虽然客户端线程在这种超
     * 时获取的模式下会出现连接无法获取的情况，但是它能够保证客户端线程不会一直挂在连接获取的操作上，
     * 而是“按时”返回，并告知客户端连接获取出现问题，是系统的一种自我保护机制。
     * 数据库连接池的设计也可以复用到其他的资源获取的场景，针对昂贵资源（比如数据库连接）的获取都应该加以超时限制
     */
}
