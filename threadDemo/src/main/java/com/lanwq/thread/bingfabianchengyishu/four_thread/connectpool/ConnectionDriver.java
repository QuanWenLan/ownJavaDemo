package com.lanwq.thread.bingfabianchengyishu.four_thread.connectpool;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @author Vin lan
 * @className ConnectionDriver
 * @description
 * @createTime 2023-02-24  14:38
 **/
public class ConnectionDriver {
    static class ConnectionHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("commit".equals(method.getName())) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }
    }
    /**
     * 创建一个 Connection 的代理，在commit时休息100毫秒
     */
    public static Connection createConnection() {
        return (Connection)Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(), new Class[]{Connection.class}, new ConnectionHandler());
    }
}
