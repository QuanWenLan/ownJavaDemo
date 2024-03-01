package com.lanwq.thread.bingfazhimei.chapter2;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Vin lan
 * @className AtomicTest
 * @description 统计 0 的个数
 * @createTime 2021-11-01  15:37
 **/
public class AtomicTest {
    /**
     * 创建 long 型原子计数器
     */
    private static AtomicLong atomicLong = new AtomicLong();
    /**
     * 创建数据源
     */
    private static Integer[] arrayOne = new Integer[]{0, 1, 2, 3, 0, 5, 6, 0, 56, 0};
    private static Integer[] arrayTwo = new Integer[]{10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {
        // (12)线程 one 统计数组  arrayOne 中 0 的个数
        Thread threadOne = new Thread(() -> {
            int size = arrayOne.length;
            for (int i = 0; i < size; i++) {
                if (arrayOne[i] == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });
        // (12)线程 two 统计数组  arrayOne 中 0 的个数
        Thread threadTwo = new Thread(() -> {
            int size = arrayTwo.length;
            for (int i = 0; i < size; i++) {
                if (arrayTwo[i] == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });

        // 启动子线程
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
        System.out.println("count 0:" + atomicLong.get());

        CopyOnWriteArrayList<Object> arrayList = new CopyOnWriteArrayList<>();
        arrayList.add("hello");
        arrayList.add("AAAA");
        arrayList.add("BBBB");
        arrayList.add("CCCC");
        arrayList.add("DDDD");
        Thread threadThree = new Thread(() -> {
            arrayList.set(1, "AAAA0000");
            arrayList.remove(2);
            arrayList.remove(3);
        });
        // 保证在修改线程启动前获取迭代器
        Iterator<Object> iterator = arrayList.iterator();
        threadThree.start();
        threadOne.join();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
