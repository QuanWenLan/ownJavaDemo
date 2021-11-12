package com.lanwq.bingfazhimei.chapter2;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Vin lan
 * @className ScheduledThreadPoolExecutorTest
 * @description
 * @createTime 2021-11-12  15:29
 **/
public class ScheduledThreadPoolExecutorTest {
    static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

    public static void main(String[] args) {
        scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("----one task----");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            throw new RuntimeException("error");
        }, 500, TimeUnit.MILLISECONDS);

        scheduledThreadPoolExecutor.schedule(() -> {
            for (int i = 0; i < 2; i++) {
                System.out.println("----two task----");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000, TimeUnit.MILLISECONDS);

        scheduledThreadPoolExecutor.shutdown();
    }
}
