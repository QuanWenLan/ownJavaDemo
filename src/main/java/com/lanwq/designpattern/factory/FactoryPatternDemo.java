package com.lanwq.designpattern.factory;

/**
 * @ClassName FactoryPatternDemo
 * @Description TODO
 * @Author lanwenquan
 * @Date 2020/1/13 16:18
 */
public class FactoryPatternDemo {
    public static void main(String[] args) {
        Shape shape = ShapeFactory.getShape("CIRCLE");
        shape.draw();

        //获取 Rectangle 的对象，并调用它的 draw 方法
        Shape shape2 = ShapeFactory.getShape("RECTANGLE");

        //调用 Rectangle 的 draw 方法
        shape2.draw();

        //获取 Square 的对象，并调用它的 draw 方法
        Shape shape3 = ShapeFactory.getShape("SQUARE");

        //调用 Square 的 draw 方法
        shape3.draw();
    }
}