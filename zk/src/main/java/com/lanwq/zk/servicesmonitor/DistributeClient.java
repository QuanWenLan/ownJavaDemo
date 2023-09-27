package com.lanwq.zk.servicesmonitor;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.checkerframework.checker.units.qual.C;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lan
 * @createTime 2023-07-23  17:26
 **/
public class DistributeClient {
    private ZooKeeper zk;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        DistributeClient client = new DistributeClient();
        // 1 获取zk连接
        client.getConnect();
        // 2 监听 services 下面的子节点增加和删除
        client.monitor();
        // 3 业务逻辑
        client.business();
    }

    private void monitor() throws InterruptedException, KeeperException {
        Stat exists = zk.exists("/services", false);
        if (exists == null) {
            System.out.println("/services 不存在！！！");
        }
        // true 走 process 方法里的
        List<String> children = zk.getChildren("/services", true);
        ArrayList<String> servers = new ArrayList<>();
        for (String child : children) {
            byte[] data = zk.getData("/services/" + child, false, null);
            servers.add(new String(data));
        }
        System.out.println("servers = " + servers);
    }

    private void business() throws InterruptedException {
        Thread.sleep(Integer.MAX_VALUE);
    }

    public void getConnect() throws IOException {
        // 获取连接
        String address = "192.168.146.128:2181,192.168.146.129:2181,192.168.146.130:2181";
        zk = new ZooKeeper(address, 60000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    monitor();
                } catch (InterruptedException | KeeperException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        System.out.println("连接成功");
    }
}
