package com.lanwq.java8.timeprocess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Vin lan
 * @className DateConvert
 * @description 时间转换
 * @createTime 2022-05-05  10:12
 **/
public class DateConvert {

    public static void main(String[] args) {
        Date date = new Date();
        dateConvert(date);
    }

    /**
     * Date转LocalDateTime、LocalDate、LocalTime
     * LocalDateTime、LocalDate、LocalTime 转成 date
     * @param date 需要转换的时间
     */
    public static void dateConvert(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("localDate : " + localDate);
        LocalTime localTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        System.out.println("localTime : " + localTime);
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("localDateTime : " + localDateTime);

        Date date1 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("localDateTime date1: " + date1);
        Date date2 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("localDate date2: " + date2);
        Date date3 = Date.from(localTime.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("localTime date3: " + date3);
    }
}
