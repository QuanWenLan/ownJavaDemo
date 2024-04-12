package com.lanwq.java8.inaction.chapter3_function_interface;


import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Lan
 * @createTime 2023-10-25  15:45
 * 构造函数方法引用
 **/
public class MethodFunctionRef {
    public static void main(String[] args) {
        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();
        System.out.println(a1);
        // 等价于
        Supplier<Apple> c12 = () -> new Apple();
        Apple apple = c12.get();

        // 有参数的话
        Function<Integer, Apple> c2 = Apple::new;
        Apple a2 = c2.apply(10);
        System.out.println(a2);
        // 等价于
        Function<Integer, Apple> c22 = (weight) -> new Apple(weight);
        Apple a22 = c2.apply(110);

        // 2个参数
        BiFunction<Integer, String, Apple> c3 = Apple::new;
        Apple a3 = c3.apply(10, "绿色");
        System.out.println(a3);

        // 静态方法可以直接引用，可以直接先用方法，然后再根据情况会推断
        Supplier<String> stringSupplier = Apple::getName;
        System.out.println(stringSupplier.get());

        // 使用方法引用将 Integer.parseInt 方法作为 Function 的实现
        Function<String, Integer> parseIntFunction = Integer::parseInt;

        // 使用函数式接口实现将字符串转换为整数
        String numberStr = "123";
        int number = parseIntFunction.apply(numberStr);

        // 输出转换后的整数
        System.out.println("Original string: " + numberStr);
        System.out.println("Parsed integer: " + number);

        // 多个参数引用
        TriFunction<Integer, Integer, Integer, Color> colorFactory = Color::new;
        Color c = colorFactory.apply(255, 0, 0);
    }
}