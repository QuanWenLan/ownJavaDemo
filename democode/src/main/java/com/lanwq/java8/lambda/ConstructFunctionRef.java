package com.lanwq.java8.lambda;


import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Lan
 * @createTime 2023-10-25  15:45
 * 构造函数方法引用
 **/
public class ConstructFunctionRef {
    public static void main(String[] args) {
        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();
        System.out.println(a1);

        Function<Integer, Apple> c2 = Apple::new;
        Apple a2 = c2.apply(10);
        System.out.println(a2);

        BiFunction<Integer, String, Apple> c3 = Apple::new;
        Apple a3 = c3.apply(10, "绿色");
        System.out.println(a3);
    }
}

class Apple {
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
}
