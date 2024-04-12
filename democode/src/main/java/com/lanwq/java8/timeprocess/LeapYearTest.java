package com.lanwq.java8.timeprocess;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * @author Vin lan
 * @className OtherTime
 * @description TODO
 * @createTime 2021-01-19  16:34
 **/
public class LeapYearTest {
    public static void main(String[] args) throws InterruptedException {
        /*long n = TimeUnit.MINUTES.toNanos(30);
        System.out.println(n);*/
        System.out.println(System.nanoTime());
        Thread.sleep(100);
        System.out.println(System.nanoTime());
        // 2024-02-28 ~ 2025-02-27
        // 平年、闰年
        LocalDate currentLocalDate = LocalDate.of(2025, 2, 28);
        System.out.println(currentLocalDate + " " + currentLocalDate.isLeapYear());
        LocalDate toDate = currentLocalDate.minusDays(1);
        System.out.println("toDate.until(currentLocalDate, ChronoUnit.DAYS) = " + toDate.until(currentLocalDate, ChronoUnit.DAYS));
        System.out.println("toDate = " + toDate);
        LocalDate fromLocalDate = currentLocalDate.minusYears(1);
        System.out.println("fromLocalDate = " + fromLocalDate);
        System.out.println(fromLocalDate.isLeapYear());
        System.out.println("fromLocalDate.lengthOfMonth() = " + fromLocalDate.lengthOfMonth());
        System.out.println(fromLocalDate.until(toDate, ChronoUnit.DAYS));

        if (currentLocalDate.isLeapYear() && !fromLocalDate.isLeapYear() &&
                currentLocalDate.getMonth() == Month.FEBRUARY && currentLocalDate.getDayOfMonth() == 29) {
            fromLocalDate = fromLocalDate.plusDays(1);
            System.out.println("2 fromLocalDate = " + fromLocalDate);
            System.out.println(fromLocalDate.until(toDate, ChronoUnit.DAYS));
        }
    }
}
