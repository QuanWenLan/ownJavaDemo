package com.lanwq.thread.bingfabianchengyishu;

import java.util.concurrent.TimeUnit;

/**
 * @author Vin lan
 * @className SleepUtils
 * @description
 * @createTime 2023-02-22  16:28
 **/
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
