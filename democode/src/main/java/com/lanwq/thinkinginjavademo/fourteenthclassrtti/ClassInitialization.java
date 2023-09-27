package com.lanwq.thinkinginjavademo.fourteenthclassrtti;

import java.util.Random;

/**
 * @author Vin lan
 * @className ClassInitialization
 * @description TODO Class对象的初始化
 * @createTime 2020-09-18  14:20
 **/

/**
 * 代码输出解释；
 *      1. **加载** ，这是由类加载器执行的。该步骤将查找字节码（通常在classpath所指定的路径中查找，但这并非是必须的），
 *      并从这些字节码中创建一个Class对象。
 * ​    2. **链接**，在链接阶段将验证类中的字节码，为静态域分配空间，并且如果必须的话，将解析这个类创建的对其他类的所有引用。
 * ​    3. **初始化**， 如果该类具有超类，则对其初始化，执行静态初始化器和静态初始化块。
 * ​    初始化被延迟到了对**静态方法（构造器隐式地是静态的）或者非常数静态域进行首次引用时才执行**。
 */
public class ClassInitialization {
    public static Random random = new Random(47);

    /**
     * 如果一个 static final值是“编译期常量”,就像Initable.staticFinal那样，那么这个值不需要对Initable类进行初始化就可以
     * 被读取。但是，如果只是将一个域设置为static和final的，还不足以确保这种行为，例如对Initable.staticFinal2的访问将
     * 强制进行类的初始化，因为它不是一个编译期常量。
     * 如果一个static域不是final的，那么在对它访问时，总是要求再被它读取之前，要先进行链接（为这个域分配存储空间）和初始化
     * （初始化该存储空间），就像对Initable2.staticNonFinal的访问所看到的那样。
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Class initable =  Initable.class;
        System.out.println("After creating Initable ref");
        // does not trigger initialization
        System.out.println(Initable.staticFinal);
        // does trigger initialization
        System.out.println(Initable.staticFinal2);
        // does not trigger initialization
        System.out.println(Initable2.staticFinal2);
        // does trigger initialization
        System.out.println(Initable2.staticNonFinal);
        // does trigger initialization,立即进行了初始化
        Class initable3 = Class.forName("com.lanwq.thinkinginjavademo.fourteenthclassrtti.Initable3");
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);
    }
}

class Initable {
    static final int staticFinal = 47;
    static final int staticFinal2 = ClassInitialization.random.nextInt(1000);

    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2 {
    static int staticNonFinal = 147;
    static final int staticFinal2 = 148;

    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {
    static int staticNonFinal = 74;

    static {
        System.out.println("Initializing Initable3");
    }
}