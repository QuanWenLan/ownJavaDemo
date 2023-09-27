package com.lanwq.zk.servicesmonitor;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author Lan
 * @createTime 2023-07-23  17:18
 * 服务上下线，先到zk上创建一个节点 create /services
 **/
public class DistributeServer {
    private ZooKeeper zk;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        DistributeServer server = new DistributeServer();
        // 1 创建连接
        server.getConnect();
        // 2 注册服务器到集群
        server.register("node129");
        // 3 启动业务逻辑
        server.business();
    }

    private void business() throws InterruptedException {
        Thread.sleep(Integer.MAX_VALUE);
    }

    private void register(String hostName) throws InterruptedException, KeeperException {
        zk.create("/services/" + hostName, hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("节点：" + hostName + " is online!");
    }

    public void getConnect() throws IOException {
        // 获取连接
        String address = "192.168.146.128:2181,192.168.146.129:2181,192.168.146.130:2181";
        zk = new ZooKeeper(address, 60000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
}
