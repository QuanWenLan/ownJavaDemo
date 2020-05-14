package com.lanwq.thinkinginjavademo.thread.concurrency;

/**
 * @program: javaDemo->Counter
 * @description: 一个测试线程访问竞争条件和临界代码段
 * @author: lanwenquan
 * @date: 2020-05-13 22:21
 */
public class Counter {
    protected int num = 0;

    public void add(int value) {
        num = num + value;
    }

    public static void main(String[] args) {
        /**
         * http://tutorials.jenkov.com/java-concurrency/race-conditions-and-critical-sections.html
         * 当有两个线程访问add方法的时候，一个线程add(2)，另一个调用add(3),最后结果应该是5，但实际上结果有可能不是 5
         * 这种情况，两个线程都要访问同一个资源 num，也就是要竞争同一个资源，称为竞态条件，add方法代码段称为 临界段
         * 参考印象笔记，并发编程学习-Race Conditions and Critical Sections
         */

    }
}

class TwoSums {

    private int sum1 = 0;
    private int sum2 = 0;

    /**
     * 注意add()方法如何向两个不同的sum成员变量添加值。为了防止竞争条件，在一个Java同步块中执行求和。在这个实现中，只有一个线程可以同时执行求和。
     */
    public void add(int val1, int val2) {
        synchronized (this) {
            this.sum1 += val1;
            this.sum2 += val2;
        }
    }
}

class TwoSums2 {

    private int sum1 = 0;
    private int sum2 = 0;

    private Integer sum1Lock = new Integer(1);
    private Integer sum2Lock = new Integer(2);

    /**
     * 现在，两个线程可以同时执行add()方法。一个线程在第一个同步块中，另一个线程在第二个同步块中。
     * 这两个同步的块在不同的对象上是同步的，所以两个不同的线程可以独立地执行这两个块。这样，线程之间相互执行add()方法的等待时间就会减少。
     */
    public void add(int val1, int val2) {
        synchronized (this.sum1Lock) {
            this.sum1 += val1;
        }
        synchronized (this.sum2Lock) {
            this.sum2 += val2;
        }
    }
}