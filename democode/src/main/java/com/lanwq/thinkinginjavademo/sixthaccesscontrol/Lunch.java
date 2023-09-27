package com.lanwq.thinkinginjavademo.sixthaccesscontrol;

/**
 * @program: ThinkingInJavaDemo -->Lunch
 * @Description : <blue>构造器为private，使用类的static成员创建该类对象</blue>
 * demonstrates class access specifiers.Make a class effectively private with private constructor
 * @author: lanwenquan
 * @creatTime: 2019-11-18 21 : 47
 **/

/*一种单例模式的写法*/
class Soup1 {
    private Soup1() {
    }

    //  允许创建一个Soup1对象，通过static方法
    public static Soup1 makeSoup() {
        return new Soup1();
    }
}

class Soup2 {
    private Soup2() {
    }

    // 创建一个 static object 返回一个引用on request（“单例模式 the 'Singleton pattern'”）
    private static Soup2 soup2 = new Soup2();

    public static Soup2 access() {
        return soup2;
    }

    void f() {
        System.out.println("f()");
    }
}

public class Lunch {

    void testPrivate() {
//        Soup1 soup1 = new Soup1();   报错，不能创建private的constructor
    }

    void testStatic() {
        Soup1 soup1 = Soup1.makeSoup(); // 允许，通过静态方法创建对象
    }

    void testSingleton() {
        Soup2.access().f();
    }
}
