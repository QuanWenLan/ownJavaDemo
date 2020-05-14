package com.lanwq.thinking_in_java_demo.eleventh_hold_the_object.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: javaDemo->ArrayAndArrayList
 * @description:
 * @author: lanwenquan
 * @date: 2020-04-23 22:16
 */
public class ArrayAndArrayList {
    public static void main(String[] args) {
        ArrayList objects = new ArrayList<>();
        objects.add(false);
        for (Object a: objects) {
            System.out.println(a);
        }

        List<Integer> list = Arrays.asList(1, 2);
        for (Object a: list) {
            System.out.println(a);
        }
    }
}
