package com.lanwq.thinking_in_java_demo.demo.sevenreusingclass;

import java.util.Random;

/**
 * @program: ThinkingInJavaDemo -->FinalData
 * @Description : <blue>final 数据</blue>
 * @author: lanwenquan
 * @creatTime: 2019-11-26 22 : 44
 **/

public class FinalData {
    private static Random random = new Random(47);
    private String  id;

    public FinalData(String id) {
        this.id = id;
    }
    //  编译时常量
    private final int valueOne = 9;
    private final int VALUE_TWO = 99;
    //  典型的public常量
    public static final int VALUE_THREE = 39;
    // 不是编译期常量
    private final int i4 = random.nextInt(20);
    static final int INT_5 = random.nextInt(20);
    // final修饰对象
    private Value v1 = new Value(1);
    private final Value v2 = new Value(2);
    private static final Value VAL_3 = new Value(3);
    // arrays
    private final int[] a = {1, 2, 3, 4, 5};

    @Override
    public String toString() {
        return id + ": " + "i4 = " + i4 + ", INT_5 = " + INT_5;
    }

    public static void main(String[] args) {
        FinalData fd1 = new FinalData("fd1");
//        fd1.valueOne++; // 报错
        fd1.v2.i++;   // 对象其自身是可以改变的
        fd1.v1 = new Value(9);
        for (int i = 0; i < fd1.a.length; i++) {
            fd1.a[i]++;  // 对象其自身是可以改变的
        }
//        fd1.v2 = new Value(2);  报错
//        VAL_3 = new Value(0);  报错
//        fd1.a = new int[3];    报错
        System.out.println(fd1);
        System.out.println("creating new FinalData");
        FinalData fd2 = new FinalData("fd2");
        System.out.println(fd1);
        System.out.println(fd2);
    }
}

class Value {
    int i;
    public Value(int i) {
        this.i = i;
    }
}