package com.lanwq.thread.bingfabianchengyishu.four_thread.connectpool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author Vin lan
 * @className ConnectionPoll
 * @description 使用等待超时默哀是来构造一个简单的数据库连接池
 * 在示例中模拟从连接池中获取、使用和释放连接的过程，而客户端获取连接的过程被设定为等待超时的模式，也就是在
 * 1000毫秒内如果无法获取到可用连接，将会返回给客户端一个null。设定连接池的大小为10个，
 * 然后通过调节客户端的线程数来模拟无法获取连接的场景。
 * @createTime 2023-02-24  14:35
 **/
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool() {
    }

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                // 连接释放后需要进行通知，这样其他消费者能够感知到连接池中已经归还了一个连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * @param mills 毫秒
     * @return 在mills内无法获取到连接，将会返回null
     * @throws InterruptedException
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
