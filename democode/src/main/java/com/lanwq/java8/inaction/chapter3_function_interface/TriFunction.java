package com.lanwq.java8.inaction.chapter3_function_interface;

/**
 * @author Lan
 * @createTime 2024-04-08  09:24
 **/
public interface TriFunction<T, U, V, R> {

    R apply(T t, U u, V v);
}
