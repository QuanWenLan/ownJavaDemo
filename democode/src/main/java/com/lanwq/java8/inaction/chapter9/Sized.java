package com.lanwq.java8.inaction.chapter9;

/**
 * @author Lan
 * @createTime 2024-04-08  14:25
 **/
public interface Sized {
    int size();

    // 默认方法
    default boolean isEmpty() {
        return size() == 0;
    }
}
