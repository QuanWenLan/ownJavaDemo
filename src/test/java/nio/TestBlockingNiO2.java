package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @program: javaDemo->TestBlockNio
 * @description: NIO的阻塞式，有相互的通知样例
 * @author: lanwenquan
 * @date: 2020-07-30 17:32
 */
public class TestBlockingNiO2 {

    // 1. 客户端
    @Test
    public void client() {
        try {
            // 1:获取通道
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8898));

            FileChannel fileChannel = FileChannel.open(Paths.get("1.png"), StandardOpenOption.READ);
            // 2：分配指定大小的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 3:将Channel中的数据读取到 Buffer中国
            while (fileChannel.read(buffer) != -1) {
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();// 每次都清空一个buffer
            }

            // 接收服务端的通知反馈
            socketChannel.shutdownOutput();// 要关闭通道，不然线程一直等待。
            int len = 0;
            while ((len = socketChannel.read(buffer)) != -1) {
                buffer.flip();
                System.out.println(new String(buffer.array(),0, len));
                buffer.clear();
            }

            // 4:关闭通道
            fileChannel.close();
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 2:服务端
    @Test
    public void server() {
        try {
            // 1: 获取通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 将数据写到本地
            FileChannel outChannel = FileChannel.open(Paths.get("3.png"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            // 2: 绑定端口号
            serverSocketChannel.bind(new InetSocketAddress(8898));
            // 3: 后去客户端连接的通道
            SocketChannel socketChannel = serverSocketChannel.accept();
            // 4: 分配指定的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 5: 从客户端的Channel读取到数据到buffer中
            while (socketChannel.read(buffer) != -1) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
            // 发送通知到客户端
            buffer.put("客户端数据传输成功".getBytes());
            buffer.flip();
            socketChannel.write(buffer); //

            // 6：关闭通道
            socketChannel.close();
            outChannel.close();
            serverSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
