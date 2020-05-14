package com.lanwq.thinking_in_java_demo.sevenreusingclass;

/**
 * @program: ThinkingInJavaDemo -->Root
 * @Description : <blue>父子类构造器练习</blue>
 * @author: lanwenquan
 * @creatTime: 2019-11-21 21 : 34
 **/

public class Root {
    private Component1 component1 = new Component1();
    private Component2 component2 = new Component2();
    private Component3 component3 = new Component3();

    public Root() {
        System.out.println("Root constructor");
    }
}

class Stream extends Root {
    private Component1 component1 = new Component1();
    private Component2 component2 = new Component2();
    private Component3 component3 = new Component3();

    public static void main(String[] args) {
        Stream stream = new Stream();
    }
}

class Component1 {
    Component1() {
        System.out.println("Component1 constructor");
    }
}

class Component2 {
    Component2() {
        System.out.println("Component2 constructor");
    }
}

class Component3 {
    Component3() {
        System.out.println("Component3 constructor");
    }
}