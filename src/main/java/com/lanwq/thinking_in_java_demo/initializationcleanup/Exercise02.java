package com.lanwq.thinking_in_java_demo.initializationcleanup;

/**
 * @ClassName Exercise02
 * @Description TODO 创建一个类，包含一个在定义时就被初始化了的string域，以及另一个通过构造器初始化的 string 域，有何差别?
 * 构造器初始化的string域更加灵活，一开始就定义了的是一个相同的值
 * @Author Administrator
 * @Date 2019/6/24  20:31
 * @Version 1.0
 * 一般不推荐将方法的参数列表顺序打乱进行重载，这会让代码难以维护，不能根据方法的返回值来判断方法的重载
 */
public class Exercise02 {
    private String s1 = "define initialization";
    private String s2;
    public Exercise02(String s){
        s2 = s;
        System.out.println("construction");
    }
    public static void main(String [] args){
        Exercise02 exercise02 = new Exercise02("Initialized at construction");
        System.out.println(exercise02.s1);
        System.out.println(exercise02.s2);
    }
}
