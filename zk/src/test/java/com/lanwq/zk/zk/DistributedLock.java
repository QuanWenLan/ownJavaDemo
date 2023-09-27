package com.lanwq.zk.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @program: javaThinkingDemo->DistributedLock
 * @description: zookeeper实现分布式锁
 * @author: lanwenquan
 * @date: 2023-05-28 16:48
 */
public class DistributedLock {
    private final String address = "192.168.146.128:2181,192.168.146.129:2181,192.168.146.130:2181";
    private ZooKeeper zk;
    private CountDownLatch connectDownLatch = new CountDownLatch(1);
    private CountDownLatch waitDownLatch = new CountDownLatch(1);
    private String waitPath;
    private String currentMode;

    public static void main(String[] args) {


    }


    public  DistributedLock(String name) throws IOException, InterruptedException, KeeperException {
        // 获取连接
        zk = new ZooKeeper(address, 40000, new Watcher() {
            // 注册了一次触发了事件之后，又继续监听。
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    connectDownLatch.countDown();
                }

                if (watchedEvent.getType() == Event.EventType.NodeDeleted && watchedEvent.getPath().equals(waitPath)) {
                    waitDownLatch.countDown();
                }
            }
        });
        connectDownLatch.await();
        System.out.println(name + "连接成功");
        // 判断根节点 /locks 是否存在
        Stat stat = zk.exists("/locks", false);
        if (stat == null) {
            // 创建根节点
            String s = zk.create("/locks", "locks".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    /**
     * 加锁
     */
    public void zkLock() throws KeeperException, InterruptedException {
        // 创建对应的临时节点。ZooDefs.Ids.OPEN_ACL_UNSAFE 是一个权限控制
        currentMode = zk.create("/locks/seq-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        // 判断创建的节点是否是最小的序号节点，如果是，获取到锁，如果不是，监听前一个节点
        List<String> children = zk.getChildren("/locks", false);
        // 如果 children 只有一个值，那就直接获取锁，如果有多个节点，需要判断
        if (children.size() == 1) {
            System.out.println("获取到锁了");
        } else {
            Collections.sort(children);
            // 获取节点名称,eg: seq-000000
            String thisNode = currentMode.substring("/locks/".length());
            int index = children.indexOf(thisNode);
            // 判断
            if (index == -1) {
                System.out.println("数据异常");
            } else if (index == 0) {
                System.out.println("获取到了锁，是第一个节点");
            } else {
                // 需要监听前一个节点的变化
                waitPath = "/locks/" + children.get(index - 1);
                zk.getData(waitPath, true, null);

                waitDownLatch.await();
            }
        }
    }

    /**
     * 解锁
     */
    public void unZkLock() throws KeeperException, InterruptedException {
        // 删除节点
        zk.delete(currentMode, -1);
    }
}
