package com.lanwq.java8.inaction.chapter3_function_interface;

import java.util.function.Function;

/**
 * @author Lan
 * @createTime 2024-04-08  09:40
 * 函数复合
 **/
public class FunctionRecombination {
    public static void main(String[] args) {
        // andThen 方法会返回一个函数，它先对输入应用一个给定函数，再对输出应用另一个函数
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);
        System.out.println(result);
        // 数学上：g(f(x)) 输出 4

        // compose 先把给定的函数用作compose的参数里面给的那个函数，然后再把函数本身用于结果。
        Function<Integer, Integer> f2 = x -> x + 1;
        Function<Integer, Integer> g2 = x -> x * 2;
        Function<Integer, Integer> h2 = f2.compose(g2);
        int result2 = h2.apply(1);
        System.out.println(result2);
        // f(g(x)) 输出 3
    }
}
