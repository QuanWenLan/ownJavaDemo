package com.lanwq.thread;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @program: javaDemo->ContainerNotSafeTest
 * @description: 集合类不安全测试
 * @author: lanwenquan
 * @date: 2022-09-28 22:16
 */
public class ContainerNotSafeTest {
    public static void main(String[] args) {
        testArrayList();
    }

    public static void testArrayList() {
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list.toString());
            }, String.valueOf(i)).start();
        }

    }

}
