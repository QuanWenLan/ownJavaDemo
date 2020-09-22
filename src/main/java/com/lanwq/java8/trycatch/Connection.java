package com.lanwq.java8.trycatch;

/**
 * @author Vin lan
 * @className Connection
 * @description TODO
 * @createTime 2020-09-03  11:11
 **/
public class Connection implements AutoCloseable {
    public Connection() {
        System.out.println("获取连接");
    }

    public void sendData() throws Exception {
        System.out.println("正在发送数据");
//        throw new Exception("send data 异常");
    }

    @Override
    public void close() throws Exception {
        System.out.println("正在关闭连接");
//        throw new Exception("close 异常");
    }
}
