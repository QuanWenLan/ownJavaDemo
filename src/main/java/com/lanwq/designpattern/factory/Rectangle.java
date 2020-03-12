package com.lanwq.designpattern.factory;

/**
 * @ClassName Rectangle
 * @Description TODO
 * @Author lanwenquan
 * @Date 2020/1/13 16:13
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}