package com.lanwq.thinkinginjavademo.testprotected;

import com.lanwq.thinkinginjavademo.sixthaccesscontrol.Animal;

/**
 * @program: ThinkingInJavaDemo -->Dog
 * @Description : <blue>测试protected的访问</blue>
 * @author: lanwenquan
 * @creatTime: 2019-11-14 22 : 45
 **/

public class Dog extends Animal {
    /*子类Cat和Dog都定义在testprotected包中，在Dog子类中通过其他子类（Cat）的对象访问父类Animal的protected成员变量和方法，不行*/
    public void show() {
        Cat cat = new Cat();
//        cat.i; //  报错  i has protected access in demo.sixthaccesscontrol
    }
}
