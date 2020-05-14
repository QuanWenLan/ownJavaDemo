package com.lanwq.thinkinginjavademo.initializationcleanup;

/**
 * @program: ThinkingInJavaDemo -->OverloadingVarargs
 * @Description : <blue>可变参数使得重载变得复杂</blue>
 *                  解决方法：给重载方法添加一个非可变参数
 * @author: lanwenquan
 * @creatTime: 2019-11-11 22 : 01
 **/

public class OverloadingVarargs {
    static void f(Character... args) {
        System.out.println("first");
        for (Character c : args) {
            System.out.print(" " + c);
        }
        System.out.println();
    }

    static void f(Integer... args) {
        System.out.println("second");
        for (Integer c : args) {
            System.out.print("" + c);
        }
        System.out.println();
    }

    static void f(Long... args) {
        System.out.print("third");
    }

    public static void main(String[] args) {
        f('a', 'v', 'c');
        f(1, 2, 3);
        f(3, 1);
        f(0);
        f(3l);
//        f();

    }
}
