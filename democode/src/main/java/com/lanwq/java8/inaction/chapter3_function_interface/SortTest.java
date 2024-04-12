package com.lanwq.java8.inaction.chapter3_function_interface;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Lan
 * @createTime 2024-04-08  09:29
 **/
public class SortTest {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        inventory.sort(new AppleComparator());

        // 2
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });

        // 3
        inventory.sort((Apple a1, Apple a2)
                -> a1.getWeight().compareTo(a2.getWeight()));
        // == 3
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        // or
        Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getWeight());
        inventory.sort(c);

        // 5 方法引用
        inventory.sort(Comparator.comparingInt(Apple::getWeight));

        // 比较后再逆序
        inventory.sort(Comparator.comparingInt(Apple::getWeight).reversed());
        // 再进行有颜色排序
        inventory.sort(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getColor));
    }

    // 1
    public static class AppleComparator implements Comparator<Apple> {
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }
}
