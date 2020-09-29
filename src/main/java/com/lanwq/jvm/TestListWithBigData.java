package com.lanwq.jvm;

import java.util.ArrayList;

/**
 * @author Vin lan
 * @className TestListWithBigData
 * @description TODO
 * @createTime 2020-09-29  14:26
 * @see <a href="https://mp.weixin.qq.com/s/0LGrSEv5MPVL0qM33T0UkQ">https://mp.weixin.qq.com/s/0LGrSEv5MPVL0qM33T0UkQ</a>
 **/
public class TestListWithBigData {
    public static void main(String[] args) {
        ArrayList<Integer> list0 = new ArrayList<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        long start0 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            list0.add(i);
        }
        System.out.println(System.currentTimeMillis() - start0); // 4007
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            list1.add(i);
        }
        System.out.println(System.currentTimeMillis() - start1); // 421
        // 两者的时间不一致, 添加jvm参数 -XX:+PrintGCDetails -XX:+PrintGCDateStamps
        // 添加虚拟机参数-Xms100M 堆的初始化大小
        /**
         * 前者时间一直都比后面的时间要长，因为后面使用了一个 OSR(On-Stack Replacement )，是一种在运行时替换正在运行的函数/方法的栈帧的技术。
         * 会对代码进行优化
         */
        // 使用两个线程
        new Thread(() -> {
            ArrayList<Integer> list00 = new ArrayList<>();
            long start00 = System.currentTimeMillis();
            for (int i = 0; i < 10000000; i++) {
                list00.add(i);
            }
            System.out.println(System.currentTimeMillis() - start00);
        }).start();

        new Thread(() -> {
            ArrayList<Integer> list01 = new ArrayList<>();
            long start01 = System.currentTimeMillis();
            for (int i = 0; i < 10000000; i++) {
                list01.add(i);
            }
            System.out.println(System.currentTimeMillis() - start01);
        }).start();

        // 当开启两个线程之后，两者的时间几乎一致
    }
}
