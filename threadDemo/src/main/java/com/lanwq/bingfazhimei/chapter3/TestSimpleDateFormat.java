package com.lanwq.bingfazhimei.chapter3;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Vin lan
 * @className TestSimpleDateFormat
 * @description
 * @createTime 2021-11-11  17:22
 **/
public class TestSimpleDateFormat {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static ThreadLocal<DateFormat> safeSdf = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            /*new Thread(() -> {
                try {
                    synchronized (sdf) {
                        System.out.println(sdf.parse("2021-11-11 17:24:30"));
                    }
//                    System.out.println(sdf.parse("2021-11-11 17:24:30"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();*/

            new Thread(() -> {
                try {
                    System.out.println(safeSdf.get().parse("2021-11-11 17:24:30"));
                } catch (ParseException e) {
                    e.printStackTrace();
                } finally {
                    safeSdf.remove();
                }
            }).start();
        }
    }
}
