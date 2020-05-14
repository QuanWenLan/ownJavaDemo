package com.lanwq.thinking_in_java_demo.initializationcleanup;

/**
 * @program: ThinkingInJavaDemo -->ExplicitStatic
 * @description: 显示的静态初始化
 * @author: lanwenquan
 * @creatTime: 2019-11-06 22 : 51
 **/

class Cup {
    Cup(int maker) {
        System.out.println("Cup(" + maker + ")");
    }

    void f(int maker) {
        System.out.println("f(" + maker + ")");
    }
}

/*静态代码块没有初始化语句的时候，按照定义的顺序进行初始化*/
class Cups {
    static {
        System.out.println("第一个static代码块");
    }

    static Cup cup1 = new Cup(1);

    static {
        System.out.println("explicit static");
//        cup1 = new Cup(2);
    }

    {
        System.out.println("非静态代码块");
    }

    Cups() {
        System.out.println("cups()");
    }
}

public class ExplicitStatic {
    public static void main(String[] args) {
        System.out.println("inside main");
        Cups.cup1.f(1);  // 并不会new一个Cups对象出来。没有打印出 “Cups()”
    }
}
