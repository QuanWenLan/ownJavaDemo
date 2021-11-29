package com.lanwq.thinkinginjavademo.fifthinitializationcleanup;

/**
 * @ClassName DefaultConstructor
 * @Description TODO 默认构造器，要是没有定义构造器，默认是一个无参构造
 * @Author Administrator
 * @Date 2019/6/24  20:52
 * @Version 1.0
 */
public class DefaultConstructor {
    public DefaultConstructor() {
        System.out.println("DefaultConstructor");
    }

    public DefaultConstructor(String s) {
        System.out.println("DefaultConstructor");
        System.out.println("s" + s);
    }

    static {
        System.out.println("static 代码");
    }

    public static void main(String[] args) {
//        new DefaultConstructor();
//        new DefaultConstructor("sssss");
        Dog dog = new Dog();
        dog.bark();
        dog.bark(1, 1.1);
        dog.bark(1.1, 1);
    }
}

class Dog {
    public void bark() {
        System.out.println("Default bark!");
    }

    public void bark(int i, double b) {
//        this.bark(); // this 只能是用在方法内部，表示对“调用方法的那个对象”的引用 可以省略
        bark();
        System.out.println("int bark = howl");
    }

    public void bark(double d, int i) {
        System.out.println("double bark = yip");
    }

}
