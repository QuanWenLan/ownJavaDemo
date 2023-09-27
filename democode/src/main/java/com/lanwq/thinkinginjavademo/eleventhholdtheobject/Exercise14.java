package com.lanwq.thinkinginjavademo.eleventhholdtheobject;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @program: javaDemo->Exercise14
 * @description: 创建一个空的LinkedList<Integer>，通过使用ListIterator，将若干个Integer插入这个list中，插入时，总是将他们插入到list的中间
 * @author: lanwenquan
 * @date: 2020-05-06 22:33
 */
public class Exercise14 {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        ListIterator<Integer> iterator = list.listIterator();
        for (int i = 1; i <= 10; i++) {
            iterator.add(i);
            if(i % 2 == 0) iterator.previous();
        }
        System.out.println(list);
    }
}
