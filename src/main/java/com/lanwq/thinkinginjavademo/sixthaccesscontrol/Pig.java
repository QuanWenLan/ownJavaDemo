package com.lanwq.thinkinginjavademo.sixthaccesscontrol;

/**
 * @program: ThinkingInJavaDemo -->Pig
 * @Description : <blue>pig</blue>
 * @author: lanwenquan
 * @creatTime: 2019-11-14 22 : 53
 **/

public class Pig extends Animal {
    void show() {
        Animal animal = new Animal();
        animal.eat();

        i = 10;
    }

    /*同包下子类中 animal对象可以访问protected属性*/
}
