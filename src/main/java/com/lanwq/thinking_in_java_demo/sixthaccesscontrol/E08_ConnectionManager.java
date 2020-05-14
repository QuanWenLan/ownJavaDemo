package com.lanwq.thinking_in_java_demo.sixthaccesscontrol;

/**
 * @program: ThinkingInJavaDemo -->E08_ConnectionManager
 * @Description : <blue>测试需求</blue>
 * @author: lanwenquan
 * @creatTime: 2019-11-18 22 : 54
 **/

public class E08_ConnectionManager {
    public static void main(String[] args) {
        Connection c = ConnectionManager.getConnection(); //
        while (c != null) {
            System.out.println(c);
            c.doSomething();
            c = ConnectionManager.getConnection();
        }
    }
}
