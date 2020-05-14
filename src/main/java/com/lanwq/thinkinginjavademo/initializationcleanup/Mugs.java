package com.lanwq.thinkinginjavademo.initializationcleanup;

/**
 * @program: ThinkingInJavaDemo -->Mugs
 * @description: 非静态实例化代码段的初始化
 * 总结：非静态实例化代码段在创建对象
 * @author: lanwenquan
 * @creatTime: 2019-11-07 22 : 45
 **/

class Mug {
    Mug(int maker) {
        System.out.println("Mug(" + maker + ")");
    }

    void f(int maker) {
        System.out.println("f(" + maker + ")");
    }
}

public class Mugs {
    Mug mug1;
    Mug mug2;
    Mug mug3 = new Mug(3);

    //    非静态代码实例块, 在每一次新建对象之后都会运行，且都是在构造方法之前执行，
//    如果使用static关键字定义那么就只会创建一次(static代码段中只能有static域)。
//    注意========= 这个也是一个方法，方法是在 全局成员变量之后再进行初始化的，全局成员变量的初始化按照定义的顺序进行初始化，都在构造方法之前
    {
        mug1 = new Mug(1);
        mug2 = new Mug(2);
        System.out.println("mug1 and mug2 initialized");
    }

    Mugs(int maker) {
        System.out.println("Mugs(maker)");
    }

    Mugs() {
        System.out.println("Mugs()");
    }

    public static void main(String[] args) {
        System.out.println("inside main");
        new Mugs();
        System.out.println("new Mugs() completed");
        new Mugs(1);
        System.out.println("new Mugs(1) completed");
    }
}
