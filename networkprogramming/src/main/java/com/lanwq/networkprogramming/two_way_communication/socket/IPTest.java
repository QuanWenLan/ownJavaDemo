package com.lanwq.networkprogramming.two_way_communication.socket;

import java.net.*;

/**
 * @ClassName IPTest
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/18 14:35
 */
public class IPTest {
    public static void main(String[] args) {
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            System.out.println(hostAddress);

            InetSocketAddress inetSocketAddress = new InetSocketAddress("10.254.11.69", 7777);
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(inetSocketAddress);
            Socket accept = serverSocket.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}