package com.lanwq.java8.inaction.chapter3_function_interface;

/**
 * @author Lan
 * @createTime 2024-03-29  10:13
 * 一个断言接口
 **/
public interface MyPredicate<T> {

    boolean test(T t);
}
