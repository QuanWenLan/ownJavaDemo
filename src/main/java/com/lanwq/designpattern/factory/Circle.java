package com.lanwq.designpattern.factory;

/**
 * @ClassName Circle
 * @Description TODO
 * @Author lanwenquan
 * @Date 2020/1/13 16:14
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}