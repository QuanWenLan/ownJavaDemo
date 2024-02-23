package com.lanwq.networkprogramming.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Vin lan
 * @className Server
 * @description Reactor模型的朴素原型：单线程Reactor模型
 * @createTime 2023-03-28  10:14
 * 参考博客： 原文链接：https://blog.csdn.net/weixin_42412601/article/details/106609038
 * https://www.cnblogs.com/crazymakercircle/p/9833847.html
 **/
public class Server {
    private static Selector selector;
    public static final int SOCKET_SERVER_PORT = 8081;
    static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void testServer() throws IOException {
        // 1、获取Selector选择器
        selector = Selector.open();
        // 2、获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 3.设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 4、绑定连接
        serverSocketChannel.bind(new InetSocketAddress(SOCKET_SERVER_PORT));
        // 5、将通道注册到选择器上,并注册的操作为：“接收”操作
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 6、采用轮询的方式，查询获取“准备就绪”的注册过的操作
        while (selector.select() > 0) {
            // 7、获取当前选择器中所有注册的选择键（“已经准备就绪的操作”）
            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
            while (selectedKeys.hasNext()) {
                // 8、获取“准备就绪”的时间
                SelectionKey selectedKey = selectedKeys.next();

                // 9、判断key是具体的什么事件
                if (selectedKey.isAcceptable()) {
                    // 10、若接受的事件是“接收就绪” 操作,就获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 11、切换为非阻塞模式
                    socketChannel.configureBlocking(false);
                    // 12、将该通道注册到selector选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectedKey.isReadable()) {
                    // 13、获取该选择器上的“读就绪”状态的通道
//                    SocketChannel socketChannel = (SocketChannel) selectedKey.channel();
//                    // 14、读取数据
//                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//                    int length = 0;
//                    while ((length = socketChannel.read(byteBuffer)) != -1) {
//                        byteBuffer.flip();
//                        System.out.println(new String(byteBuffer.array(), 0, length));
//                        byteBuffer.clear();
//                    }
//                    socketChannel.close();
                    // 也可以这样写，另外的线程来处理读数据
                    readData(selectedKey);
                }
                // 15、移除选择键
                selectedKeys.remove();
            }
        }
        // 7、关闭连接
        serverSocketChannel.close();
    }

    //读取客户端消息
    private static void readData(SelectionKey key) {
        SocketChannel channel = null;

        try {
            //取到关联的channel
            //得到channel
            channel = (SocketChannel) key.channel();
            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int count = channel.read(buffer);
            //把缓存区的数据转成字符串
            String msg = new String(buffer.array());
            executorService.execute(() -> {
                System.out.println("业务逻辑处理线程: " + Thread.currentThread().getName());
                //根据count的值做处理
                if (count > 0) {
                    //输出该消息
                    System.out.println("form 客户端: " + msg);
                    System.out.println("-----------------------------------------------------------");
                }
            });
            //向其它的客户端转发消息(去掉自己), 专门写一个方法来处理
            sendInfoToOtherClients(msg, channel);
        } catch (Exception e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了..");
                //取消注册
                key.cancel();
                //关闭通道
                channel.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    //转发消息给其它客户(通道)
    private static void sendInfoToOtherClients(String msg, SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中...");
        System.out.println("服务器转发数据给客户端线程: " + Thread.currentThread().getName());

        //遍历 所有注册到selector 上的 SocketChannel,并排除 self
        for (SelectionKey key : selector.keys()) {
            //通过 key  取出对应的 SocketChannel
            Channel targetChannel = key.channel();

            //排除自己
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                //转型
                SocketChannel dest = (SocketChannel) targetChannel;
                //将msg 存储到buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                //将buffer 的数据写入 通道
                dest.write(buffer);
            }
        }

    }

    //也可以写一个Handler
    class MyHandler {
        public void readData() {

        }
        public void sendInfoToOtherClients(){

        }
    }

    public static void main(String[] args) throws IOException {
        testServer();
    }
}
