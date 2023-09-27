package com.lanwq.java8.timeprocess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Vin lan
 * @className LocalDateTimeTest
 * @description TODO
 * @createTime 2020-09-22  17:44
 * LocalDateTime是一个不可变的日期-时间对象，它既包含了日期同时又含有时间，默认格式是 yyyy-MM-ddTHH-mm-ss.zzz
 **/
public class LocalDateTimeTest {
    public static void main(String[] args) {
        //获取当前的日期时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("当前的日期时间：" + localDateTime);

        //获取自定义的的日期时间
        LocalDateTime specifiedDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println("自定义的日期时间：" + specifiedDateTime);

        System.out.println("时间转化为LocalDate和LocalTime");
        //转化为LocalDate和LocalTime
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println("当前日期：" + localDate);
        System.out.println("当前时间：" + localTime);
    }
}
