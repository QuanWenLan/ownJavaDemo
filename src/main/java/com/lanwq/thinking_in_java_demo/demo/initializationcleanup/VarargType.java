package com.lanwq.thinking_in_java_demo.demo.initializationcleanup;

/**
 * @ClassName VarargType
 * @Description TODO 可变参数列表
 * @Author Administrator
 * @Date 2019/8/1  22:05
 * @Version 1.0
 */
public class VarargType {
    static void f(Character... args) {
        System.out.print(args.getClass());
        System.out.println(" length " + args.length);
    }

    static void g(int... args) {
        System.out.print(args.getClass());
        System.out.println(" length " + args.length);
    }

    public static void main(String[] args) {
        f('a');
        f();
        g(1);
        g();
        System.out.println(" int[] " + new int[0].length);
    }
}
