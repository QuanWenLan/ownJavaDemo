package com.lanwq.thread.example.sharingresource;

/**
 * @author Vin lan
 * @className EvenGenerator
 * @description
 * @createTime 2021-03-15  15:42
 **/
public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    // 递增不是原子性操作
    @Override
    public int next() {
        ++currentEvenValue; // danger point here，会发生并发问题，如下面描述的那样
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
    /**
     * 一个任务有可能在另一个任务执行第一个对currentEvenValue的递增操作之后，但是没有执行第二个
     * 操作之前，调用next()方法（即注释为“danger point here”的地方）。这将使这个值处于“不恰当”的状态。为了证明这是可能发生的，
     * EvenChecker.test(new EvenGenerator())创建了一组EvenChecker对象，以连续地读取并输出同一个EvenChecker，并测试检查每个数值
     * 是否都是偶数。如果不是，就会报告错误，程序也将关闭。
     * 这个程序最终将失败，因为各个EvenChecker任务在EvenChecker处于“不恰到的”状态时，仍然能够访问其中的信息。
     */
}
