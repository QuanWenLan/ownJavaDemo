package com.lanwq.thinking_in_java_demo.nine_interface;

/**
 * @program: javaDemo->E14_InterfaceInheritance
 * @description: 14l练习
 * @author: lanwenquan
 * @date: 2020-03-22 22:05
 */
public class E14_InterfaceInheritance {
    static void take1(One one) {
        one.oneMethodInOneInterface();
        one.twoMethodInOneInterface();
    }

    static void take2(Two two) {
        two.oneMethodInTwoInterface();
        two.twoMethodInTwoInterface();
    }

    static void take3(Three three) {
        three.oneMethodInThreeInterface();
        three.twoMethodInThreeInterface();
    }

    static void takesAll(Inherit inherit) {
        inherit.oneMethodInOneInterface();
        inherit.twoMethodInOneInterface();

        inherit.oneMethodInTwoInterface();
        inherit.twoMethodInTwoInterface();

        inherit.oneMethodInThreeInterface();
        inherit.twoMethodInThreeInterface();
    }

    public static void main(String args[]) {
        Concrete concrete = new Concrete();
        take1(concrete);
        take2(concrete);
        take3(concrete);
        takesAll(concrete);
    }

}
