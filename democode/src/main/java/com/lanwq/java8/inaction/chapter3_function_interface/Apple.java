package com.lanwq.java8.inaction.chapter3_function_interface;

/**
 * @author Lan
 * @createTime 2024-03-29  11:02
 **/
public class Apple {
    private int weight;
    private String color;

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public Apple() {
        System.out.println("默认");
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }

    public static String getName() {
        return "Apple";
    }

    public Integer getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }
}
