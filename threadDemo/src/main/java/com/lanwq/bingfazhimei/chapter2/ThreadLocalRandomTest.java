package com.lanwq.bingfazhimei.chapter2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Vin lan
 * @className ThreadLocalRandomTest
 * @description
 * @createTime 2021-10-29  17:06
 **/
public class ThreadLocalRandomTest {
    public static void main(String[] args) {
        // （10）创建一个默认种子的随机数生成器
        ThreadLocalRandom random = ThreadLocalRandom.current();
        // （11）输出 10 个在0-5（包含0，不包含5）之间的随机数
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(5));
        }
    }
}
