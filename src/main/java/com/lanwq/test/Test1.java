package com.lanwq.test;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @program: javaDemo->Test1
 * @description:
 * @author: lanwenquan
 * @date: 2020-04-25 15:01
 */
public class Test1 {
    /**
     * 假设一个字符串只含有字符 * 和 az 字母,写一段代码，把所有的字符 * 放到字母的左边，字母则按照原有顺序都放在右边
     * <p>
     * 输入：*c*m*b*n*t*
     * 输出：******cmbnt
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().trim();
        // 求出输入的字符串长度
        int length = s.length();
        // 将所有的 * 替换成 空格
        StringBuilder sbStar = new StringBuilder();
        StringBuilder sbCase = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if ("*".equals(String.valueOf(s.charAt(i)))) {
                sbStar.append(s.charAt(i));
            } else if ("abcdefghijklmnopqrstuvwxyz".contains(String.valueOf(s.charAt(i)))) {
                sbCase.append(s.charAt(i));
            }
        }
        System.out.println(sbStar.toString() + sbCase.toString());
    }

}
