package com.lanwq.thinking_in_java_demo.initializationcleanup;

/**
 * @ClassName AutoBoxingVarargs
 * @Description TODO 自动包装机制 可变参数
 * @Author Administrator
 * @Date 2019/8/1  22:14
 * @Version 1.0
 */
public class AutoBoxingVarargs {
    public static void f(Integer... args) {
        for (Integer i : args) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        f(new Integer(1), new Integer(2));
        f(1, 2, 3, 5);
        f(10, new Integer(19));
    }
}
