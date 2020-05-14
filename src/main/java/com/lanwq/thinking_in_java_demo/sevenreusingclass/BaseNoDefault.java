package com.lanwq.thinking_in_java_demo.sevenreusingclass;

/**
 * @program: ThinkingInJavaDemo -->BaseNoDefault
 * @Description : <blue>没有默认构造器的基类</blue>
 * @author: lanwenquan
 * @creatTime: 2019-11-21 21 : 27
 **/
import static net.mindview.util.Print.print;

public class BaseNoDefault {
    public BaseNoDefault(int i) {
        print(i);
        print("BaseNoDefault constructor");
    }

    public static void main(String[] args) {
        ExtendBase e = new ExtendBase();
        ExtendBase e2 = new ExtendBase(2);
    }
}

class ExtendBase extends BaseNoDefault {
    public ExtendBase() {
        super(1);   // 显示调用基类的构造器
        print("ExtendBase default constructor");
    }

    public ExtendBase(int i) {
       super(i);
        print("ExtendBase args constructor");
    }
}