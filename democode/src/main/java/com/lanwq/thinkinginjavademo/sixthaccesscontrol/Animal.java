package com.lanwq.thinkinginjavademo.sixthaccesscontrol;

/**
 * @program: ThinkingInJavaDemo -->Animal
 * @Description : <blue>验证protected的访问权限</blue>
 * @author: lanwenquan
 * @creatTime: 2019-11-14 22 : 34
 **/

public class Animal {

    protected int i = 5;

    protected void eat() {
        System.out.println("Animals eat.");
    }

}
