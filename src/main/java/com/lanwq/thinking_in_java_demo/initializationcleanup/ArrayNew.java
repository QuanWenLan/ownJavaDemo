package com.lanwq.thinking_in_java_demo.initializationcleanup;

import java.util.Arrays;
import java.util.Random;

/**
 * @program: ThinkingInJavaDemo -->ArrayNew
 * @Description : 创建新数组时的为赋初始值的情况
 * @author: lanwenquan
 * @creatTime: 2019-11-11 21 : 29
 **/

public class ArrayNew {
    public static void main(String[] args) {
        int[] a;
        Random random = new Random(47);
        a = new int[random.nextInt(20)];
        System.out.println("数组a：" + Arrays.toString(a));

        StringContainer[] sc = {new StringContainer("1"), new StringContainer("2")};
        StringContainer[] sc2 = new StringContainer[2];
        StringContainer[] sc3 = new StringContainer[]{};
    }

    /*数组初始化*/
    public void arrayInitialization() {
        int[] a = new int[2];
        Integer[] a2 = {new Integer(1), new Integer(2), 3};   // 自动装箱机制，包装成了 Integer 对象
        Integer[] a3 = new Integer[]{1, 3, 3};
    }
}

class StringContainer {
    public StringContainer(String str) {
        System.out.println("str: " + str);
    }
}
