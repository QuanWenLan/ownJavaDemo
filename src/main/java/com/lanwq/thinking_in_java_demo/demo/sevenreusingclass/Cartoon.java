package com.lanwq.thinking_in_java_demo.demo.sevenreusingclass;

/**
 * @program: ThinkingInJavaDemo -->Cartoon
 * @Description : <blue>父子类初始化</blue>
 * 过程：从基类“向外”扩散的，所以积累在导出类构造器可以访问它之前，就已经完成了初始化。存在静态代码块的时候，顺序：
 * 最底层基类的静态代码块 -> 。。。->最上层的静态代码块，还有一个非静态代码块类似于一个变量申明，每new一个对象就会运行一次
 * 其他的顺序和 demo/initializationcleanup/ExplicitStatic.java中描述的一样
 * @author: lanwenquan
 * @creatTime: 2019-11-20 22 : 42
 **/

class Art {
    public Art() {
        System.out.println("art constructor");
    }

    static {
        System.out.println("art 静态代码实例块");
    }

    {
        System.out.println("art 非静态代码实例块");
    }
}

class Drawing extends Art {
    public Drawing() {
        System.out.println("drawing constructor");
    }

    static {
        System.out.println("Drawing 静态代码实例块");
    }

    {
        System.out.println("Drawing 非静态代码实例块");
    }
}

public class Cartoon extends Drawing {
    public Cartoon() {
        System.out.println("cartoon constructor");
    }

    static {
        System.out.println("Cartoon 静态代码实例块");
    }

    public static void main(String[] args) {
        Cartoon cartoon = new Cartoon();
        new Cartoon();
    }
}
