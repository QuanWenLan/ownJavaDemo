package com.lanwq.thinkinginjavademo.fourteenthclassrtti.exercise;

/**
 * @program: javaDemo->Exercise9
 * @description: 写一个程序，使它能判断char数组究竟是个基本类型还是一个对象
 * @author: lanwenquan
 * @date: 2020-05-12 20:36
 */
public class Exercise9 {
    public static void main(String[] args) {
        char[] ac = "hello, world".toCharArray();
        System.out.println("ac.getClass() = " + ac.getClass());
        System.out.println("ac.getSuperClass() = " + ac.getClass().getSuperclass());

        char c = 'c';
//        System.out.println("c.getClass() = " + c.getClass());  // 编译不通过
        int[] ia = new int[3];
        System.out.println("ia.getClass() = " + ia.getClass());
        long[] la = new long[3];
        System.out.println("la.getClass() = " + la.getClass());
        double[] da = new double[3];
        System.out.println("da.getClass() = " + da.getClass());
        String[] sa = new String[3];
        System.out.println("sa.getClass() = " + sa.getClass());
        Exercise9[] pot = new Exercise9[3];
        System.out.println("pot.getClass() = " + pot.getClass()); // Multi-dimensional arrays: int[][][] thread = new int[3][][];
//        System.out.println("thread.getClass() = " + thread.getClass());
    }
}
