package nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @program: javaDemo->TestBuffer
 * @description: Buffer测试
 * @author: lanwenquan
 * @date: 2020-05-08 22:07
 */
public class TestBuffer {
    @Test
    public  void testBuffer() {
        /**
         * capacity:容量，表示缓冲区中最大存储数据的容量，一旦申明不能更改。
         * limit：界限，表述缓冲区可以操作数据的大小，limit后面的数据不能进行读写
         * position：位置，表示缓冲区中在操作数据的位置
         */
        String str = "abcde";
        //  1. 分配一个ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println("=================put()=================");
        // 2. 存入数据到缓冲区中 put()，写数据模式
        buffer.put(str.getBytes());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println("==================flip()================");
        // 3. 切换成读取数据模式
        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println("================get()==================");
        // 4. 从缓冲区中获取数据 get()
        byte[] dst = new byte[buffer.limit()];
        buffer.get(dst); //读取数据到一个字节数组中。
        System.out.println(new String(dst, 0 , dst.length));

        System.out.println("================rewind()==================");
        // 5:rewind()  可重复读，回归到初始状态
        buffer.rewind();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println("================clear()===================");
        // 6: clear() 清空缓冲区，但是缓冲区的数据依然存在，但是处于“被遗忘”状态
        buffer.clear();
        System.out.println((char)buffer.get());
    }

    /**
     * 分配直接缓冲区，将缓冲区建立在内存中，提高效率
     */
    @Test
    public void testDirectMemory() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.println(buffer.isDirect());
    }

    /**
     * 测试mark标记方法和reset方法
     */
    @Test
    public void test2() {
        String str = "abcde";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());
        byte b = 'f';
        buffer.put(b);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("===============");
        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes, 0, 2);
        System.out.println(new String(bytes,0 ,2));
        System.out.println(buffer.position());
        System.out.println("===============");
        // mark();标记
        buffer.mark();
        buffer.get(bytes, 2, 2);
        System.out.println(new String(bytes, 2, 2));
        System.out.println(buffer.position());
        System.out.println("===============");
        //reset():恢复到mark的位置
        buffer.reset();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        if (buffer.hasRemaining()) {
            System.out.println(buffer.remaining());
        }
    }
}
