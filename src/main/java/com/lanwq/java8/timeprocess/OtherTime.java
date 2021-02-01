package com.lanwq.java8.timeprocess;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Vin lan
 * @className OtherTime
 * @description TODO
 * @createTime 2021-01-19  16:34
 **/
public class OtherTime {
    public static void main(String[] args) throws InterruptedException {
        /*long n = TimeUnit.MINUTES.toNanos(30);
        System.out.println(n);*/
        System.out.println(System.nanoTime());
        Thread.sleep(100);
        System.out.println(System.nanoTime());
    }
}
