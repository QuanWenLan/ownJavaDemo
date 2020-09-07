package com.lanwq.trycatch;

/**
 * @author Vin lan
 * @className Connection
 * @description TODO
 * @createTime 2020-09-03  11:11
 **/
public class Connection2 implements AutoCloseable {
    public Connection2() {
        System.out.println("获取连接");
    }

    public void sendData() throws Exception {
        throw new Exception("send data 异常");
    }

    @Override
    public void close() throws Exception {
        throw new Exception("close 异常");
    }
}
