package com.lanwq.test;

/**
 * @program: javaDemo->MyStack
 * @description: 自己使用数组实现栈
 * 使用数组实现栈
 * 自己实现一个栈，要求这个栈具有push()、pop()（返回栈顶元素并出栈）、peek() （返回栈顶元素不出栈）、isEmpty()、size()这些基本的方法。
 *
 * 提示：每次入栈之前先判断栈的容量是否够用，如果不够用就用Arrays.copyOf()进行扩容；
 * @author: lanwenquan
 * @date: 2020-03-31 20:18
 */
public class MyStack {
    private int[] storage;//存放栈中元素的数组
    private int capacity;//栈的容量
    private int count;//栈中元素数量
    private static final int GROW_FACTOR = 2;

    // 不带初始容量的初始化的栈
    public MyStack() {
        this.count = 0;
        this.capacity = 8;
        this.storage = new int[this.capacity];
    }

    // 初始化时带容量的栈
    public MyStack(int capacity) {
        this.count = 0;
        this.capacity = capacity;
        this.storage = new int[this.capacity];
    }

    // push操作
    public void push(int value) {
        this.storage[count++] = value; // 新增一个容量就加1
    }


}
