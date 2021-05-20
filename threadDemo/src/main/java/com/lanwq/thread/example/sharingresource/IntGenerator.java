package com.lanwq.thread.example.sharingresource;

/**
 * @author Vin lan
 * @className IntGenerator
 * @description 与 EvenChecker解耦，参考页面 p677
 * @createTime 2021-03-15  15:21
 **/
public abstract class IntGenerator {
    // 因为是boolean类型的，所以它的操作是原子性的。即诸如赋值和返回值这样的简单操作在发生时没有中断的可能
    // 为了保证可见性，还定义成了 volatile 标志
    private volatile boolean canceled = false;
    public abstract int next();   //
    // allow this to be canceled;
    public void cancel() {
        this.canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
