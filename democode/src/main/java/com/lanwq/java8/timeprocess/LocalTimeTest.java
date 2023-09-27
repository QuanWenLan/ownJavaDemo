package com.lanwq.java8.timeprocess;

import java.time.LocalTime;

/**
 * @author Vin lan
 * @className LocalTimeTest
 * @description TODO
 * @createTime 2020-09-22  17:37
 * LocalTime与LocalDate一样，也是一个不可变的类，默认格式是 hh:mm:ss.zzz ，它提供了对时间的各种操作
 **/
public class LocalTimeTest {
    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        System.out.println(now);
        System.out.println("====================");
        getNow();
        System.out.println("====================");
        testTimeOperator();
        System.out.println("====================");
        testDateCompare(LocalTime.now(), LocalTime.now().minusHours(1));
    }

    public static void getNow() {
        //获取当前时间、时、分、秒以及自定义时间
        LocalTime localTime = LocalTime.now();
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();
        System.out.println("当前时间：" + localTime);
        System.out.println("时：" + hour + "  分：" + minute + "  秒：" + second);

        //获取自定义时间
        LocalTime specifiedTime = LocalTime.of(15, 30, 45);
        System.out.println("自定义时间：" + specifiedTime);
    }

    public static void testDateCompare(LocalTime localDate, LocalTime otherDate) {
        // 比较两个时间是否相等
        System.out.println(localDate+ "," + otherDate);
        //equals方法用于比较两个日期是否相等
        if (localDate.equals(otherDate)) {
            System.out.println("localDate与otherDate相等！");
        } else {
            //isAfter和isBefore方法用于比较两个日期前后顺序
            if (localDate.isAfter(otherDate)) {
                System.out.println("localDate晚于otherDate！");
            }
            if (localDate.isBefore(otherDate)) {
                System.out.println("localDate早于otherDate！");
            }
        }
    }

    public static void testTimeOperator() {
        LocalTime localTime = LocalTime.now();
        System.out.println("当前时间：" + localTime);
        System.out.println("2小时后时间：" + localTime.plusHours(2));
        System.out.println("30分钟后时间：" + localTime.plusMinutes(30));
        System.out.println("500秒后日时间：" + localTime.plusSeconds(500));

        System.out.println("2小时前时间：" + localTime.minusHours(2));
        System.out.println("30分钟前时间：" + localTime.minusMinutes(30));
        System.out.println("500秒前时间：" + localTime.minusSeconds(500));
    }
}
