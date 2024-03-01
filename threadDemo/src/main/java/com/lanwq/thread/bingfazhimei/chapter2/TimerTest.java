package com.lanwq.thread.bingfazhimei.chapter2;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Vin lan
 * @className TimerTest
 * @description
 * @createTime 2021-11-12  14:58
 **/
public class TimerTest {
    static Timer timer = new Timer();

    public static void main(String[] args) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("----one task----");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("error");
            }
        }, 500);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (; ; ) {
                    System.out.println("----one task----");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 500);
    }
}
