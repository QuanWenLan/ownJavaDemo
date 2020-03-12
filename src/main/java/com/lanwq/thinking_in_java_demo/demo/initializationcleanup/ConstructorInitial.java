package com.lanwq.thinking_in_java_demo.demo.initializationcleanup;

/**
 * @ClassName ConstructorInitial
 * @Description TODO 构造器初始化
 * @Author Administrator
 * @Date 2019/6/24  21:46
 * @Version 1.0
 */
public class ConstructorInitial {
    /*变量定义初始化会在任何方法（包括构造器）被调用之前得到初始化*/
    public static void main(String [] args){
        House h = new House();
        h.f(); // 最后打印
    }
}

class Window{
    Window(int maker){
        System.out.println("Window("+ maker + ")");
    }
}

class House{
    Window w1 = new Window(1);
    House(){
        System.out.println("house()");
        w3 = new Window(33);
    }
    Window w2 = new Window(2);
    void f(){
        System.out.println("f()");
    }
    Window w3 = new Window(3);
}
