package com.lanwq.zk.zk.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException;

/**
 * @program: javaThinkingDemo->CuratorTest
 * @description: curator方式
 * @author: lanwenquan
 * @date: 2023-05-28 22:17
 */
public class CuratorTest {
    public static void main(String[] args) {
        ExponentialBackoffRetry policy = new ExponentialBackoffRetry(3000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.146.128:2181,192.168.146.129:2181,192.168.146.130:2181")
                .connectionTimeoutMs(40000)
                .sessionTimeoutMs(40000)
                .retryPolicy(policy)
                .build();
        client.start();
        System.out.println("zookeeper 启动");
        //  创建分布式锁1
        InterProcessMutex lock1 = new InterProcessMutex(client, "/locks");
        InterProcessMutex lock2 = new InterProcessMutex(client, "/locks");

        new Thread(() -> {
            try {
                lock1.acquire();
                System.out.println("线程1 获取到锁");

                lock1.acquire();
                System.out.println("线程1 再次获取到锁");

                Thread.sleep(5 * 1000);

                lock1.release();
                System.out.println("线程1 释放锁");


                lock1.release();
                System.out.println("线程1 再次释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "线程1").start();

        new Thread(() -> {
            try {
                lock2.acquire();
                System.out.println("线程2 获取到锁");

                lock2.acquire();
                System.out.println("线程2 再次获取到锁");

                Thread.sleep(5 * 1000);

                lock2.release();
                System.out.println("线程2 释放锁");


                lock2.release();
                System.out.println("线程2 再次释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "线程2").start();
    }
}
