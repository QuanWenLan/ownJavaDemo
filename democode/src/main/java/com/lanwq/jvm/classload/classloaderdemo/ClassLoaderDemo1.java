package com.lanwq.jvm.classload.classloaderdemo;

/**
 * @program: javaDemo->ClassLoaderDemo1
 * @description: 获取jvm的三种类加载器
 * @author: lanwenquan
 * @date: 2021-03-06 10:44
 */
public class ClassLoaderDemo1 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 父子关系  AppClassLoader < ExtClassLoader < BootStrap ClassLoader(), 左边的儿子辈，向右逐渐是其父亲，最右边的是最高的父类加载器
        final ClassLoader classLoader1 = ClassLoaderDemo1.class.getClassLoader();
        System.out.println("classLoader1 -> " + classLoader1);
        System.out.println("parent of classLoader1 -> " + classLoader1.getParent());
        // BootStrap ClassLoader由C++开发，是JVM的一部分，但是不属于Java类
        System.out.println("grant parent of classLoader1 -> " + classLoader1.getParent().getParent());
        // string, int 等类是由BootStrap ClassLoader加载的
        final ClassLoader stringClassLoader = String.class.getClassLoader();
        System.out.println("string class loader -> " + stringClassLoader);
        System.out.println(classLoader1.loadClass("java.util.List").getClass().getClassLoader());

        //java 指令可以通过增加 -verbose:class -verbose:gc 参数在启动时打印出类加载情况
        // BootStrap ClassLoader 加载Java的基础类，这个属性不能再Java指令中指定，推断不是由Java语言处理
        System.out.println("BootStrap ClassLoader 加载目录： " + System.getProperty("sun.boot.class.path"));
        // ExtClassLoader 加载java_home/ext目录下的jar包，可通过 -D java.ext.dirs另行指定目录
        System.out.println("Extension ClassLoader 加载目录： " + System.getProperty("java.ext.dirs"));
        // AppClassLoader 加载classpath，应用下的jar，可通过-D java.class.path另行指定目录
        System.out.println("Application ClassLoader 加载目录： " + System.getProperty("java.class.path"));
    }

    // 每种类加载器都有其自己的加载目录

}
