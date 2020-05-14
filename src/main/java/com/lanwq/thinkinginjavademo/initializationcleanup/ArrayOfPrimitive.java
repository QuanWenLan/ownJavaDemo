package com.lanwq.thinkinginjavademo.initializationcleanup;

/**
 * @program: ThinkingInJavaDemo -->ArrayOfPrimitive
 * @Description : 数组原始值复制
 * @author: lanwenquan
 * @creatTime: 2019-11-07 23 : 25
 **/

public class ArrayOfPrimitive {
    public static void main(String[] args) {
        int [] a1 = {1, 2, 3, 4, 5};
        int [] a2;
        a2 = a1; // 复制的只是数组的一个引用（为该数组所分配的数组空间的引用）
        for (int i = 0; i < a2.length; i++) {
            a2[i] = a2[i] + 1;
        }

        for (int j = 0; j < a1.length; j++) {
            System.out.print(" a1[" + j + "] = " + a1[j]);
        }
        int [] a3 = new int[3];
    }
}
