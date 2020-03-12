package com.lanwq.thinking_in_java_demo.demo.controllingexecuting;

import java.util.Properties;

/**
 * @ClassName Assignment
 * @Description TODO 对象的引用
 * @Author Administrator
 * @Date 2019/6/17  20:24
 * @Version 1.0
 */
public class Assignment {
    public static void main(String[] args) {
        Tank t1 = new Tank();
        Tank t2 = new Tank();
        t1.level = 1;
        t2.level = 2;
        System.out.println(t1.level);
        t1 = t2; // 赋值操作传递的是引用，
        System.out.println("t1 level:" + t1.level + " t2 level:" + t2.level);
        System.out.println(t1 == t2);
        if (t1.level <= 2) {
            System.out.println("小");
        }
        Properties properties = System.getProperties();
        System.out.println(properties.toString());
    }
}

class Tank {
    int level;
}