package com.lanwq.java8.inaction.chapter3_function_interface;

/**
 * @author Vin lan
 * @className MyInterface
 * @description TODO 自定义一个函数式接口, 并带有参数和返回值，这个即是类似于内置的 Function，
 * 接收一些值，返回一个值
 * @createTime 2020-10-10  15:49
 **/
public interface MyFunctionWithParamWithReturn {
    int apply(int x, int y);
}
