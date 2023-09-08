package guava.collection.immutable;

import com.google.common.collect.ImmutableSet;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Lan
 * @createTime 2023-09-04  14:12
 * 不可变集合测试类
 **/
public class Test {
    static Set<String> bars;

    static {
        bars = new HashSet<>();
        bars.add("red");
        bars.add("orange");
        bars.add("yellow");
        bars.add("green");
        bars.add("blue");
        bars.add("purple");
    }

    public void example1() {
        ImmutableSet<String> COLOR_NAMES = ImmutableSet.of("red", "orange", "yellow", "green", "blue", "purple");
        ImmutableSet<String> COLOR_NAMES2 = ImmutableSet.copyOf(bars);
    }
}
