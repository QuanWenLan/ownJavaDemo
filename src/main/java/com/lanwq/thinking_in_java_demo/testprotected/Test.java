package com.lanwq.thinking_in_java_demo.testprotected;

/**
 * @program: ThinkingInJavaDemo -->Test
 * @Description : <blue>测试protected访问权限</blue>
 * @author: lanwenquan
 * @creatTime: 2019-11-14 22 : 48
 **/

public class Test {
    /*子类Cat定义在testprotected包中，同包中其他类中通过子类的对象访问父类Animal的protected成员变量和方法，不行*/
    public static void main(String[] args) {
        Cat cat = new Cat();
//        cat.i; //  报错  i has protected access in demo.sixthaccesscontrol
    }
}
