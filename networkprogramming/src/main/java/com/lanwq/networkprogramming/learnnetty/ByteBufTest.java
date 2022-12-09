package com.lanwq.networkprogramming.learnnetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author Vin lan
 * @className ByteBufTest
 * @description 测试案例
 * @createTime 2022-12-06  14:03
 **/
public class ByteBufTest {
    public static void main(String[] args) {
        // 初始化容量为9，最大100 个字节
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        print("allocate bytebuf(9,100)", buffer);

        // write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可以写
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        print("writeBytes(new byte[]{1, 2, 3, 4})", buffer);

        // write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可以写，写完 int 后，写指针增加 4
        buffer.writeInt(12);
        print("writeInt(12)", buffer);

        // write 方法改变写指针，写完之后写指针=capacity 的时候，buffer 不可写
        buffer.writeBytes(new byte[]{5});
        print("writeBytes(new byte[] {5})", buffer);

        // write 方法改变写指针，写完之后写指针=capacity 的时候，buffer 不可写则开始扩容，扩容后capacity随即改变
        buffer.writeBytes(new byte[]{6});
        print("writeBytes(new byte[] {6})", buffer);

        // get 方法不改变读写指针
        System.out.println("getByte(3) return:" + buffer.getByte(3));
        System.out.println("getShort(3) return:" + buffer.getShort(3));
        System.out.println("getInt(3) return:" + buffer.getInt(3));
        print("getByte()", buffer);

        // set 方法不改变读写指针
        buffer.setByte(buffer.readableBytes() + 1, 0);
        print("setByte()", buffer);

        // read 方法改变都指针
        byte[] dst = new byte[buffer.readableBytes()];
        buffer.readBytes(dst);
        print("readBytes(" + dst.length + ")", buffer);
    }

    private static void print(String action, ByteBuf buffer) {
        System.out.println("after ====== " + action + " ======");
        System.out.println("capacity():" + buffer.capacity());
        System.out.println("maxCapacity():" + buffer.maxCapacity());
        System.out.println("readerIndex():" + buffer.readerIndex());
        System.out.println("readableBytes():" + buffer.readableBytes());
        System.out.println("isReadable():" + buffer.isReadable());
        System.out.println("writerIndex():" + buffer.writerIndex());
        System.out.println("writableBytes():" + buffer.writableBytes());
        System.out.println("isWritable():" + buffer.isWritable());
        System.out.println("maxWritableBytes():" + buffer.maxWritableBytes());
        System.out.println();
    }
}
