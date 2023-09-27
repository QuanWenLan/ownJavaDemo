package com.lanwq.jvm.classload;

/**
 * @author Vin lan
 * @className NotInitialization
 * @description TODO
 * @createTime 2020-12-18  17:09
 **/

/**
 * 非主动使用类字段演示
 */
public class NotInitialization {
    public static void main(String[] args) {
//        System.out.println(SuperClass.VALUE);
        /**
         * 被动使用类字段演示二：
         * 通过数组定义来引用类，不会触发此类的初始化
         */
//        SuperClass [] sca = new SuperClass[2];
        /**
         * 三
         */
        System.out.println(ConstantClass.HELLO_WORLD);
    }
}

/**
 * 被动使用类字段演示一：
 * 通过子类引用父类的静态字段，不会导致子类初始化
 */
class SuperClass {
    static {
        System.out.println("SuperClass init!");
    }
    public static int VALUE = 123;
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}

