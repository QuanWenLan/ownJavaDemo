package com.lanwq.thinkinginjavademo.ninthinterface;

/**
 * @program: javaDemo->Concreate
 * @description:
 * @author: lanwenquan
 * @date: 2020-03-22 21:58
 */
public class Concrete extends SuperConcrete implements Inherit {

    @Override
    public void methodInInherit() {
        System.out.println("methodInInherit");
    }

    @Override
    public void oneMethodInOneInterface() {
        System.out.println("oneMethodInOneInterface");
    }

    @Override
    public void twoMethodInOneInterface() {
        System.out.println("twoMethodInOneInterface");
    }

    @Override
    public void oneMethodInThreeInterface() {
        System.out.println("oneMethodInThreeInterface");
    }

    @Override
    public void twoMethodInThreeInterface() {
        System.out.println("twoMethodInThreeInterface");
    }

    @Override
    public void oneMethodInTwoInterface() {
        System.out.println("oneMethodInTwoInterface");
    }

    @Override
    public void twoMethodInTwoInterface() {
        System.out.println("twoMethodInTwoInterface");
    }

}

class SuperConcrete {
    public void show() {

    }
}