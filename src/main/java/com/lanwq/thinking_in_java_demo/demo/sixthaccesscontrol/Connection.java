package com.lanwq.thinking_in_java_demo.demo.sixthaccesscontrol;

/**
 * @program: ThinkingInJavaDemo -->Connection
 * @Description : <blue>ConnecionManager的管理子类</blue>
 * @author: lanwenquan
 * @creatTime: 2019-11-18 22 : 47
 **/

public class Connection {
    private static int counter = 0;  // counter 类一旦创建就不变
    private int id = counter++;      // 使用counter来计数 id

    // 包访问权限
    Connection() {
    }

    @Override
    public String toString() {
        return "Connection " + id;
    }

    public void doSomething() {
        System.out.println("do something...");
    }

}
