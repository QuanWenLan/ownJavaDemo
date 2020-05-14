package com.lanwq.thinking_in_java_demo.sixthaccesscontrol;

/**
 * @program: ThinkingInJavaDemo -->ConnectionManager
 * @Description : <blue>private构造器练习</blue> Exercise 8
 * 管理connection的管理类
 * * Following the form of the example Lunch.java,
 * * create a class called ConnectionManager that
 * * manages a fixed array of Connection objects.
 * * The client programmer must not be able to
 * * create Connection objects, but only get them
 * * via a static method in ConnectionManager.
 * * ConnectionManager returns a null reference when
 * * it runs out of objects. Test the classes in main()
 * @author: lanwenquan
 * @creatTime: 2019-11-18 22 : 46
 **/

public class ConnectionManager {
    private static Connection[] pool = new Connection[10];
    private static int counter = 0;
    // 创建 ConnectionManager 的时候就创建connection数组,  涉及类的构造器初始化顺序,static成员一旦有使用就初始化，但是有可能该类不会初始化
    static {
        for (int i = 0; i < pool.length; i++) {
            pool[i] = new Connection();
        }
    }

    public static Connection getConnection() {
        if (counter < pool.length)
            return pool[counter++];
        return null;
    }

}

