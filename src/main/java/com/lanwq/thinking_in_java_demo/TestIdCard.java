package com.lanwq.thinking_in_java_demo;


import java.util.Scanner;

/**
 * @ClassName TestIdCard
 * @Description TODO 验证身份证号
 * @Author Administrator
 * @Date 2019/10/22  21:15
 * @Version 1.0
 */
public class TestIdCard {
    public static void main(String[] args) {
        // 输入一个身份证号，确认是不是符合规范
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个身份证号：");
        String s = sc.nextLine();
        System.out.println("身份证号：" + legal(s));
    }

    public static boolean legal(String idCard) {
        if (idCard.length() != 18) {
            return false;
        }
        if ('0' == idCard.charAt(0) || 'X' == idCard.charAt(idCard.length() - 1)) {
            return false;
        }
        for (int i = 0; i < idCard.length(); i++) {
            if (idCard.charAt(i) < '0' || idCard.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean legal2(String idCard) {
//        Pattern
        return true;
    }
}
