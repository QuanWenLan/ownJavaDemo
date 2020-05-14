package nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @program: javaDemo->TestBufferr
 * @description: Buffer测试
 * @author: lanwenquan
 * @date: 2020-05-08 22:07
 */
public class TestBuffer {
    @Test
    public  void testBuffer() {
        String str = "abcde";
        //  1. 分配一个ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("==================================");
        // 2. 存入数据到缓冲区中 put()
        buffer.put(str.getBytes());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        // 3. 切换成读取数据模式
        buffer.flip();

        // 4. 从缓冲区中获取数据 get()
        byte[] dst = new byte[buffer.limit()];
        buffer.get(dst);
        System.out.println(new String(dst, 0 , dst.length));

        // 5:rewind()  可重复读

        // 6: clear() 清空缓冲区，但是缓冲区的数据依然存在，但是处于“被遗忘”状态

    }
}
