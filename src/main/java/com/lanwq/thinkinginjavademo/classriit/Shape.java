package com.lanwq.thinkinginjavademo.classriit;

/**
 * @program: javaDemo->Shape
 * @description:
 * @author: lanwenquan
 * @date: 2020-04-05 11:20
 */
public class Shape {
    private String name;

    public Shape() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("Shape");
            Object o = aClass.newInstance(); // 只能返回Object
            Shape s = (Shape)o;

            Class<Shape> shapeClass = Shape.class;
            Shape shape = shapeClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
