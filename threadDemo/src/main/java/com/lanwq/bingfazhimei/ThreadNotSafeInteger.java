package com.lanwq.bingfazhimei;

/**
 * @author Vin lan
 * @className ThreadNotSafeInteger
 * @description
 * @createTime 2021-10-29  12:03
 **/
public class ThreadNotSafeInteger {
    private volatile int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }
}
