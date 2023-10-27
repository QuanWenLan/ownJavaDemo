package com.lanwq.java8.lambda;

import com.google.common.base.Function;
import com.google.common.base.Supplier;

import java.util.function.BiFunction;

/**
 * @author Lan
 * @createTime 2023-10-25  15:45
 * 构造函数方法引用
 **/
public class ConstructFunctionRef {
    public static void main(String[] args) {
        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();

        Function<Integer, Apple> c2 = Apple::new;
        Apple a2 = c2.apply(10);

        BiFunction<Integer, String, Apple> c3 = Apple::new;
        Apple a3 = c3.apply(10, "绿色");
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
}
