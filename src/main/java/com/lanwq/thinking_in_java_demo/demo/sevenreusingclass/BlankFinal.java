package com.lanwq.thinking_in_java_demo.demo.sevenreusingclass;

/**
 * @program: ThinkingInJavaDemo -->BlankFinal
 * @Description : <blue>空白final</blue>
 * @author: lanwenquan
 * @creatTime: 2019-11-27 22 : 05
 **/

public class BlankFinal {
    private final Integer i;

    public BlankFinal(Integer i) {
        this.i = i;
    }

    public Integer getI() {
//        if (this.i == null)
//            i =  47;  // 不能重新分配值给final修饰的域
            return i;
    }

    public static void main(String[] args) {
        BlankFinal blankFinal = new BlankFinal(20);
        System.out.println(blankFinal.getI());
    }
}
