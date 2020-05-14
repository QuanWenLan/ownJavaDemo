package com.lanwq.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: javaDemo->Test2
 * @description:
 * @author: lanwenquan
 * @date: 2020-04-25 15:29
 */
public class Test2 {
    public static void main(String[] args) {
        /**
         * 假设n个整数，求出n个整数中有多少个不同的子集合和为24
         * 每组的第一行包括一个整数n 1<=n<=23
         * 第二行包括n个整数 1<=整数<=23
         *
         * 5
         * 1 1 2 22 23
         */
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        if (i >= 1 && i <= 23) {
            String line = sc.nextLine();
            String[] s = line.split(" ");
            List<Integer> ins = new ArrayList<>(24);
            for (int j = 0; j < s.length; j++) {
                if(Integer.parseInt(s[i]) >= 24 || Integer.parseInt(s[i]) < 0) {
                    return;
                }
                if (!" ".equals(s[i])) {
                    ins.add(Integer.parseInt(s[i]));
                }
            }
            // 整数要小于等于 23

        }
    }
}
