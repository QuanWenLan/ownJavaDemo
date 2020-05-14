package com.lanwq.thinking_in_java_demo.thread.concurrency;

/**
 * @program: javaDemo->Counter2
 * @description:
 * @author: lanwenquan
 * @date: 2020-05-13 22:59
 */
public class Counter2 {
    protected int num = 0;

    public void add(int value) {
        num = num + value;
    }

    public static void main(String[] args) {
        Counter2 counter2 = new Counter2();
        new MyThread1(counter2).start();
        new MyThread2(counter2).start();
        new MyThread3(counter2).start();
    }
}

class MyThread1 extends Thread {
    private Counter2 counter2;

    public MyThread1() {
    }

    public MyThread1(Counter2 counter2) {
        super("线程1");
        this.counter2 = counter2;
    }

    @Override
    public void run() {
        System.out.println("线程" + getName() + "调用 add(3) num = " + counter2.num);
        this.counter2.add(3);
        System.out.println(this.counter2.num);
    }
}

class MyThread2 extends Thread {
    private Counter2 counter2;

    public MyThread2() {
    }

    public MyThread2(Counter2 counter2) {
        super("线程2");
        this.counter2 = counter2;
    }

    @Override
    public void run() {
        System.out.println("线程" + getName() + "调用 add(2) num = " + counter2.num);
        this.counter2.add(2);
        System.out.println(this.counter2.num);
    }
}

class MyThread3 extends Thread {
    private Counter2 counter2;

    public MyThread3() {
    }

    public MyThread3(Counter2 counter2) {
        super("线程3");
        this.counter2 = counter2;
    }

    @Override
    public void run() {
        System.out.println("线程" + getName() + "调用 add(2) num = " + counter2.num);
        this.counter2.add(2);
        System.out.println(this.counter2.num);
    }
}