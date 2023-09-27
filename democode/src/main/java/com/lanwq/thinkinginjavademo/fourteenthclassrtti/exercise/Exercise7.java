package com.lanwq.thinkinginjavademo.fourteenthclassrtti.exercise;

import java.lang.reflect.Field;

/**
 * @program: javaDemo->Exercise7
 * @description: 一个方法，可以接收任意对象作为参数，并能够递归打印该对象所在的继承体系中的所有类
 * @author: lanwenquan
 * @date: 2020-05-12 20:07
 */
public class Exercise7 {
    static void printAll(Class cc) {
        if (null != cc) {
            System.out.println(cc.getName());
            for (Class c : cc.getInterfaces()) {
                System.out.println("interface: " + c.getName());
                printAll(c);
            }
            Class superclass = cc.getSuperclass();
            printAll(superclass);

            Field[] declaredFields = cc.getDeclaredFields();
            if (declaredFields.length != 0) {
                for (Field field : declaredFields)
                    System.out.println(field);
            }
        }
        return;
    }

    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("com.lanwq.thinkinginjavademo.demo.class_riit.FancyToy");
        } catch (ClassNotFoundException e) {
            System.out.println("cannot find");
        }
        printAll(c);
    }
}
