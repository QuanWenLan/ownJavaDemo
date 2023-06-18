package com.lanwq.zk.zk;

import org.apache.zookeeper.KeeperException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @program: javaThinkingDemo->DistributedLockTest
 * @description: 测试
 * @author: lanwenquan
 * @date: 2023-05-28 18:47
 */
public class DistributedLockTest {
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        DistributedLock lock1 = new DistributedLock("客户端1");
        DistributedLock lock2 = new DistributedLock("客户端2");
        System.out.println("主线程启动完成！！！");
        new Thread(() -> {
            try {
                lock1.zkLock();
                System.out.println("线程1 获取到锁");
                Thread.sleep(5000);
                lock1.unZkLock();
                System.out.println("线程1 释放锁");
            } catch (InterruptedException | KeeperException e) {
                e.printStackTrace();
            }
        }, "线程1").start();

        new Thread(() -> {
            try {
                lock2.zkLock();
                System.out.println("线程2 获取到锁");
                Thread.sleep(5000);
                lock2.unZkLock();
                System.out.println("线程2 释放锁");
            } catch (InterruptedException | KeeperException e) {
                e.printStackTrace();
            }
        }, "线程2").start();
    }
}
