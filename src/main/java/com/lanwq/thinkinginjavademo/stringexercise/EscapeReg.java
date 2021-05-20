package com.lanwq.thinkinginjavademo.stringexercise;

import java.util.regex.Pattern;

/**
 * @author Vin lan
 * @className EscapeReg
 * @description 转义和正则
 * @createTime 2021-03-19  17:09
 **/
public class EscapeReg {
    public static void main(String[] args) {
        testEscape();
/*        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1")  + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);*/
        String s1 = "1";
        String s2 = "2";
        System.out.println(s1 + s2);
    }

    private static final Pattern PICKUP_ID_PATTERN = Pattern.compile("[\\dA-Z]*");
    private static final Pattern p2 = Pattern.compile("^#\\d+");

    /**
     * 测试转义
     */
    private static void testEscape() {
        String testStr = "Room 501, Wing Tuch Commercial Centre,\n" +
                "177-183 Wing Lok Street,\n" +
                "Sheung Wan\\/";
        String afterReplace = testStr.replaceAll("\\n|%n", " ");
        System.out.println(afterReplace);
        String s = afterReplace.replaceAll("[\\\\/]", " bbb");
        System.out.println(s);

        System.out.println("正则： " + PICKUP_ID_PATTERN.matcher("852M").find());
        System.out.println(p2.matcher("#4").matches());
    }
}
