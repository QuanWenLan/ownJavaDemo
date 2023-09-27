package nio;

/**
 * @program: javaDemo->TestChannel
 * @description: NIO的通道
 * @author: lanwenquan 负责缓冲区中数据的传输，本身不存储任何数据，因此需要配合缓冲区进行传输
 * @date: 2020-06-01 21:53
 */

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 2：通道的主要实现了类
 * java.nio.channels.Channel 接口：
 * |-- fileChannel(本地IO)
 * |-- SocketChannel (网络 数据的IO，TCP)
 * |-- ServerSocketChannel (网络 数据的IO，TCP)
 * |-- DatagramChannel（UDP）
 * 3： 获取通道
 * （1）Java针对支持通道的类提供了  getChannel()  方法
 * 本地IO：FileInputStream、FileOutPutStream、RandomAccessFile
 * 网络IO：Socket、ServeSocket、DatagramSocket
 * （2）在JDK1.7中的NIO.2针对各个通道提供了静态方法  FileChannel.open()
 * (3)在JDK1.7中的NIO.2的Files工具类的newByteChannel()
 */
public class TestChannel {

    //1:使用Channel来传输数据（非直接缓冲区）
    @Test
    public void test1() {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
//            fileInputStream = new FileInputStream("E:\\project\\IdeaProjects\\javaDemo\\src\\test\\java\\1.png");
            String c = this.getClass().getResource("/").getPath();
            fileInputStream = new FileInputStream(c + "/1.png");
//            fileOutputStream = new FileOutputStream("E:\\project\\IdeaProjects\\javaDemo\\src\\test\\java\\2.png");
            fileOutputStream = new FileOutputStream(c + "/2.png");

            // 1：获取通道。channel只是通道，操作数据是要用缓冲区来操作
            inChannel = fileInputStream.getChannel();
            outChannel = fileOutputStream.getChannel();

            // 2：将通道中的数据存入缓冲区中
            ByteBuffer dst = ByteBuffer.allocate(1024);
            while (inChannel.read(dst) != -1) {
                // 3：将缓冲区切换成数据为读数据的模式
                dst.flip();
                outChannel.write(dst);
                dst.clear();// 这次读取完数据之后，清空dst，所有的数据limit，position，回归到默认
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 2：测试使用直接缓冲区来完成数据的操作(直接放到内存映射文件中，也就是直接缓冲区)
    @Test
    public void test2() throws IOException {
        long start = System.currentTimeMillis();
        // 获取一个输入通道
        FileChannel inChannel = FileChannel.open(Paths.get("D:\\迅雷下载\\FSDSS-021AV.mp4"), StandardOpenOption.READ);
        // 获取一个输出通道
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\迅雷下载\\FSDSS-0212AV.mp4"), StandardOpenOption.WRITE, StandardOpenOption.READ,
                StandardOpenOption.CREATE);

        // 内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        // 直接对缓冲区进行数据的读写操作(相对于第一个test1，前面使用的通道读数据，通道写数据。)
        byte[] buffer = new byte[inMappedBuf.limit()];
        inMappedBuf.get(buffer);
        outMappedBuf.put(buffer);

        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end - start));
    }

    // 通道之间的数据传输
    @Test
    public void test3() throws IOException {
        // 获取一个输入通道
        FileChannel inChannel = FileChannel.open(Paths.get("D:\\迅雷下载\\FSDSS-021AV.mp4"), StandardOpenOption.READ);
        // 获取一个输出通道
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\迅雷下载\\FSDSS-0212AV.mp4"), StandardOpenOption.WRITE, StandardOpenOption.READ,
                StandardOpenOption.CREATE);

        inChannel.transferTo(0, inChannel.size(), outChannel);
        // 或者
        // outChannel.transferFrom(inChannel,0 ,inChannel.size());

        inChannel.close();
        outChannel.close();
    }

    // 分散读取和聚集写入
    @Test
    public void test4() throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("d:/1.txt", "rw");
        // 1：获取通道
        FileChannel channel = randomAccessFile.getChannel();
        // 2：创建多个缓冲区，分配指定大小的缓冲区
        ByteBuffer buffer1 = ByteBuffer.allocate(100);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);

        //3：读取数据到缓冲区数组
        ByteBuffer[] bufs = {buffer1, buffer2};
        channel.read(bufs); // 将通道中的数据读取到（也就是写入）buffer数组中

        // 切换buffer的读数据模式
        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }

        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("=================================");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        // 聚集写入
        RandomAccessFile randomAccessFile1 = new RandomAccessFile("d:/2.txt", "rw");
        FileChannel channel1 = randomAccessFile1.getChannel();
        channel1.write(bufs);
    }

    // 字符集,
    // 编码：字符串 -》 字节数组
    // 解码：字节数组 -》字符串
    @Test
    public void test5() throws IOException {
        /*SortedMap<String, Charset> charsets = Charset.availableCharsets();
        for (Map.Entry<String, Charset> charsetEntry : charsets.entrySet()) {
            System.out.println(charsetEntry.getKey() + " : " + charsetEntry.getValue());
        }*/

        Charset charset = Charset.forName("GBK");
        //获取编码器
        CharsetEncoder charsetEncoder = charset.newEncoder();
        //获取解码器
        CharsetDecoder charsetDecoder = charset.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("无敌威武霸气");
        charBuffer.flip();// 切换成读数据模式

        // 编码,字符转成字节
        ByteBuffer bBuffer = charsetEncoder.encode(charBuffer);

        for (int i = 1; i <= 12; i++) {
            System.out.println(bBuffer.get());
        }

        bBuffer.flip();
        CharBuffer decodeCharBuf = charsetDecoder.decode(bBuffer);
        System.out.println(decodeCharBuf.toString());
    }
}
