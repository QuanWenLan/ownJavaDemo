package com.lanwq.thinkinginjavademo.initializationcleanup;

/**
 * @program: ThinkingInJavaDemo->StaticInitialization
 * @description: 静态数据初始化顺序： 如果类中存在静态的变量申明，那么在第一次创建对象或者第一次使用对象的时候，
 * 申明的静态变量就会进行初始化。这里如果是new一个对象的时候，对象中的成员变量也会申明（顺序和申明的顺序有关）；
 * 如果是第一次使用这个类的时候，静态变量申明就会进行初始化，成员变量并不会进行初始化。最后再进行构造器的初始化，
 * 而构造器的初始化（变量定义的顺序决定了初始化的顺序）。
 * 简单来说：先静态对象，而后是“非静态”对象。静态代码块也是和静态数据成员一样 static { // 这里面可以对静态数据成员进行初始化 }
 * @author: lanwenquan
 * @create: 2019-11-05 22:22
 **/
class Bowl {
    Bowl(int maker) {
        System.out.println("Bowl(" + maker + ")");
    }

    void f1(int maker) {
        System.out.println("Bowl(" + maker + ")");
    }
}

class Table {
    static Bowl bowl = new Bowl(1);

    Table() {
        System.out.println("Table()");
        bow2.f1(1);
    }

    void f2(int maker) {
        System.out.println("f2(" + maker + ")");
    }

    static Bowl bow2 = new Bowl(2);
}

class Cupboard {
    Bowl bow3 = new Bowl(3); // 创建对象的时候进行初始化
    static Bowl bow4 = new Bowl(4);  // 静态数据先初始化

    Cupboard() { // 最后构造函数
        System.out.println("Cupboard()");
        bow4.f1(2);
    }

    void f3(int maker) {
        System.out.println("f3(" + maker + ")");
    }

    static Bowl bow5 = new Bowl(5); // 静态数据先初始化
}

public class StaticInitialization {
    public static void main(String[] args) {
        System.out.println("Creating new Cupboard() in main");
        new Cupboard(); // 这个时候Cupboard()里面的 静态数据已经初始化了，不会再进行初始化
        System.out.println("Creating new Cupboard() in main");
        new Cupboard();
        table.f2(1);
        cupboard.f3(1);
    }

    static Table table = new Table();
    static Cupboard cupboard = new Cupboard();
}
