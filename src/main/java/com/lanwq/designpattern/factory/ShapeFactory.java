package com.lanwq.designpattern.factory;

/**
 * @ClassName ShapeFactory
 * @Description TODO 画图的工厂
 * @Author lanwenquan
 * @Date 2020/1/13 16:16
 */
public class ShapeFactory {
    public static Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}