package com.lanwq.networkprogramming.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Lan
 * @createTime 2024-02-23  10:26
 * reactor 模式，客户端， 参考：https://blog.csdn.net/weixin_42412601/article/details/106609038
 **/
public class Client {
    public static void testClient() throws IOException {
        // 1、获取Selector选择器
        Selector selector = Selector.open();
        // 2、获取通道，连接服务器
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(Server.SOCKET_SERVER_PORT));
        // 3.设置为非阻塞
        socketChannel.configureBlocking(false);
        // 4、绑定连接（这里不需要绑定）

        // 5、将通道注册到选择器上,并注册的操作为：“读”操作
        socketChannel.register(selector, SelectionKey.OP_READ);
        // 每隔3s 读取服务器发送的数据
        new Thread(() -> {
            while (true) {
                try {
                    // 6、采用轮询的方式，查询获取“准备就绪”的注册过的操作
                    while (selector.select() > 0) {
                        // 7、获取当前选择器中所有注册的选择键（“已经准备就绪的操作”）
                        Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                        while (selectedKeys.hasNext()) {
                            // 8、获取“准备就绪”的时间
                            SelectionKey selectedKey = selectedKeys.next();
                            // 9、判断key是具体的什么事件
                            if (selectedKey.isReadable()) {
                                // 13、获取该选择器上的“读就绪”状态的通道
                                SocketChannel sc = (SocketChannel) selectedKey.channel();
                                // 14、读取数据
                                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                int length = 0;
                                while ((length = sc.read(byteBuffer)) != -1) {
                                    byteBuffer.flip();
                                    System.out.println(new String(byteBuffer.array(), 0, length));
                                    byteBuffer.clear();
                                }
                                sc.close();
                            }
                            // 15、移除选择键
                            selectedKeys.remove();
                        }
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        String username = socketChannel.getLocalAddress().toString().substring(1);
        // 发送消息
        //发送数据给服务器端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String info = username + " 说：" + s;
            try {
                socketChannel.write(ByteBuffer.wrap(info.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 7、关闭连接
        socketChannel.close();
    }

    public static void main(String[] args) throws IOException {
        testClient();
    }
}
