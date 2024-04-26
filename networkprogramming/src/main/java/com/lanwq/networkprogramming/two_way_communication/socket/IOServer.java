package com.lanwq.networkprogramming.two_way_communication.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Vin lan
 * @className IOServer
 * @description
 * @createTime 2022-12-02  16:53
 **/
public class IOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        // 收新连接线程
        new Thread(() -> {
            while (true) {
                try {
                    // 1 阻塞获取新连接
                    Socket socket = serverSocket.accept();
                    // 2 为每一个新连接创建一个新线程负责读取数据
                    new Thread(() -> {
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            // 3 按字节流方式读取数据
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /**
     * 这里有个问题：在传统的IO模型中，每个连接创建成功之后都需要由一个线程来维护，每个线程都包含一个while死循环，
     * 那么1万个连接对应1万个线程，继而有1万个while死循环。
     * 1.线程资源受限：线程是操作系统中非常宝贵的资源，同一时刻有大量的线程处于阻塞状态，是非常严重的资源浪费，操作系统耗不起。
     * 2.线程切换效率低下：单机CPU核数固定，线程爆炸之后操作系统频繁进行线程切换，应用性能急剧下降。
     * 3.除了以上两个问题，在IO编程中，我们看到数据读写是以字节流为单位的
     */
}
