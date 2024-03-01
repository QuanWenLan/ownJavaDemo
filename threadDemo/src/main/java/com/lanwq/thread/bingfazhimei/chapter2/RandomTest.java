package com.lanwq.thread.bingfazhimei.chapter2;

import java.util.Random;

/**
 * @author Vin lan
 * @className RandomTest
 * @description
 * @createTime 2021-10-29  16:50
 **/
public class RandomTest {
    public static void main(String[] args) {
        // （1）创建一个默认种子的随机数生成器
        Random random = new Random();
        // （2）输出 10 个在0-5（包含0，不包含5）之间的随机数
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(5));
        }
    }
}
