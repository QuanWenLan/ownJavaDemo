package com.lanwq.thinking_in_java_demo.demo.testprotected;

import com.lanwq.thinking_in_java_demo.demo.sixthaccesscontrol.Animal;

/**
 * @program: ThinkingInJavaDemo -->Cat
 * @Description : <blue>猫继承animal</blue>
 * @author: lanwenquan
 * @creatTime: 2019-11-14 22 : 35
 **/

public class Cat extends Animal {

    public void eat() {
        /*子类定义在testprotected中 在子类中直接使用Animal的protected成员变量和方法 */
        i = 7;
        eat();
        System.out.println("cat is eating" + i);

        /*子类定义在testprotected中 在子类中通过子类的对象可以直接使用Animal的protected成员变量和方法 */
        Cat cat = new Cat();
        cat.i = 10;
        cat.eat();
        System.out.println("cat is eating" + i);
    }

    public void show() {
        /*子类定义在testprotected中 在子类中通过父类的对象不能访问Animal的 protected成员变量和方法 */
        Animal animal = new Animal();
        /*animal.i = 20;   // 报错 i has protected access in demo.sixthaccesscontrol
        animal.eat();   //  报错
        System.out.println("cat is eating" + animal.i); //  报错*/
    }

    /*
    *1. 子类可以通过继承获得不同包父类的protected权限成员变量和成员方法，在子类中可以直接访问

    2. 在子类中可以通过子类的对象访问父类的protected成员变量和方法

    3. 在子类中反而不能通过父类的对象访问父类的protected成员变量和方法

    4. 在子类中不能通过其他子类的对象访问父类的protected成员变量和方法

    5. 在与子类同包的其他类中不能通过子类的对象访问父类的protected成员变量和方法
    ————————————————
    版权声明：本文为CSDN博主「blacktal」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/blacktal/article/details/81198579
    * */
}
