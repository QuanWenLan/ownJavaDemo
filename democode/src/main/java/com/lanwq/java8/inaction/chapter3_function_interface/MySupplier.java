package com.lanwq.java8.inaction.chapter3_function_interface;

/**
 * @author Lan
 * @createTime 2024-03-29  10:47
 * 有其他的一些 BooleanSupplier、IntSupplier、LongSupplier、Supplier
 **/
public interface MySupplier<T> {
    T get();
}
