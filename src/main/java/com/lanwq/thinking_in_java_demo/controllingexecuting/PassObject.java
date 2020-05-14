package com.lanwq.thinking_in_java_demo.controllingexecuting;

/**
 * @ClassName PassObject
 * @Description TODO 方法的别名问题
 * @Author Administrator
 * @Date 2019/6/20  22:25
 * @Version 1.0
 */
public class PassObject {
    private static void f(Letter y){
        y.c = 'z';
    }
    public  static void main(String [] args){
        Letter x = new Letter();  // x 指向new Letter()这个对象的地址
        x.c = 'a';
        System.out.println("x.c:" + x.c);
        // 当 x 作为参数传递的时候，传递的是对这个对象地址的一个 引用
        // 修改的时候实际上是修改了 new Letter() 这个对象中 c 的实际值
        f(x);
        System.out.println("x.c:" + x.c);
    }
}

class Letter{
    char c;
}