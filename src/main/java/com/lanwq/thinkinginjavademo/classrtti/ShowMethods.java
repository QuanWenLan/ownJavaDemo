package com.lanwq.thinkinginjavademo.classrtti;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author Vin lan
 * @className ShowMethods
 * @description TODO 类方法提取器
 * Using reflecting to show all the methods of a class
 * even if the methods are defined in the base class
 * @createTime 2020-10-13  15:48
 **/
class ShowMethods {
    private static String usage = "usage:show methods";
    private static Pattern p = Pattern.compile("\\w+\\.");
    private static Pattern p2 = Pattern.compile("\\w+\\.|native\\s|final\\s");

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println(usage);
            System.exit(0);
        }
        int line = 0;
        try {
            Class<?> c = Class.forName(args[0]);
            Method[] methods = c.getMethods();
            Constructor<?>[] constructors = c.getConstructors();
            if (args.length == 1) {
                for (Method method : methods) {
                    System.out.println(p.matcher(method.toString()).replaceAll(""));
                }
                for (Constructor constructor : constructors) {
                    System.out.println(p.matcher(constructor.toString()).replaceAll(""));
                }
            } else {
                for (Method method : methods) {
                    if (method.toString().contains(args[1])) {
                        System.out.println(p.matcher(method.toString()).replaceAll(""));
                    }
                }
                for (Constructor constructor : constructors) {
                    if (constructor.toString().contains(args[1])) {
                        System.out.println(p.matcher(constructor.toString()).replaceAll(""));
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("no such class:" + e);
        }
    }
}
