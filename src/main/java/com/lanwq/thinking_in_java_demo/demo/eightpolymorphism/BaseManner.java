package com.lanwq.thinking_in_java_demo.demo.eightpolymorphism;

/**
 * @program: ThinkingInJavaDemo -->BaseManner
 * @Description : <blue></blue>
 * @author: lanwenquan
 * @creatTime: 2019-12-01 17 : 38
 **/

public class BaseManner {
    public static void f(BaseManner baseManner) {
        baseManner.g();
    }

    public void g() {
        System.out.println("baseManner.g()");
    }

    public static void main(String[] args) {
        BaseManner one = new GeneratedBaseOne();
        f(one);
    }
}

class GeneratedBaseOne extends BaseManner {
    public void g() {
        System.out.println("GeneratedBaseOne.g()");
        f();
    }

    public void f(){

    }
}