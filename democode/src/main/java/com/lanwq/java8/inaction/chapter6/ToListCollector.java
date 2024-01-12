package com.lanwq.java8.inaction.chapter6;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import static java.util.stream.Collector.Characteristics.*;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    // 创建集合操作的起始点
    @Override
    public Supplier<List<T>> supplier() {
        return () -> new ArrayList<T>();
    }

    // 累计遍历过的项目，原位修改累加器
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return (list, item) -> list.add(item);
    }

    // 恒等函数
    @Override
    public Function<List<T>, List<T>> finisher() {
        return i -> i;
    }

    // 修改第一个累加器，将其与第二个累加器的内容合并
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    // 为收集器添加IDENTITY_FINISH 和 CONCURRENT
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
    }
}
