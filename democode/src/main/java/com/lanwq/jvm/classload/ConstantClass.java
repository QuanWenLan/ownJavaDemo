package com.lanwq.jvm.classload;

/**
 * @author Vin lan
 * @className ConstantClass
 * @description TODO
 * @createTime 2020-12-18  17:27
 **/
/**
 * 被动使用类字段演示三：
 * 常量在编译阶段会存入调用类的常量池中，本质上并没有引用到定义常量的类，因此不会触发定义的常量的类的初始化
 */
public class ConstantClass {
    static {
        System.out.println("ConstantClass init!");
    }

    public static final String HELLO_WORLD = "hello world";
}
