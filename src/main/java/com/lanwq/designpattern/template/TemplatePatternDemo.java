package com.lanwq.designpattern.template;

/**
 * @ClassName TemplatePatternDemo
 * @Description TODO 模板方法模式测试类
 * @Author lanwenquan
 * @Date 2020/1/13 14:52
 */
public class TemplatePatternDemo {
    public static void main(String[] args) {
        Game cricket = new Cricket();
        cricket.play();
        System.out.println();
        Game football = new Football();
        football.play();
    }
}