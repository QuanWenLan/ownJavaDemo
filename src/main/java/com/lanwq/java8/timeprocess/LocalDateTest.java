package com.lanwq.java8.timeprocess;

import java.time.LocalDate;

/**
 * @author Vin lan
 * @className LocalDateTest
 * @description TODO
 * @createTime 2020-09-22  17:07
 * LocalDate是一个不可变类，在不考虑时区的情况下可以对日期（不包括时间）进行各种操作，它的默认格式是 yyyy-MM-dd
 * @see <a href="https://www.javazhiyin.com/22875.html">https://www.javazhiyin.com/22875.html</a>
 **/
public class LocalDateTest {
    public static void main(String[] args) {
        testGetNow();
        System.out.println("=========================");
        testSpecifiedDate();
        System.out.println("=========================");
        LocalDate localDate = LocalDate.now();
        LocalDate otherDate = LocalDate.of(2020, 9, 21);
        testDateCompare(localDate, otherDate);
        System.out.println("=========================");
        testDateOperator();
        System.out.println("=========================");
        testDateInterval();
    }

    public static void testGetNow() {
        // 获取当前日期及年、月、日
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int monthValue = localDate.getMonthValue();
        int dayOfMonth = localDate.getDayOfMonth();
        System.out.println("日期：" + localDate + "，年：" + year + "月：" + monthValue + "日：" + dayOfMonth);
    }

    public static void testSpecifiedDate() {
        // 获取指定的日期
        LocalDate localDate1 = LocalDate.of(2020, 9, 22);
        System.out.println("指定日期：" + localDate1);
    }

    public static void testDateCompare(LocalDate localDate, LocalDate otherDate) {
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

    public static void testDateOperator() {
        LocalDate localDate = LocalDate.now();
        LocalDate localDateAfterDay = localDate.plusDays(1);
        testDateCompare(localDate, localDateAfterDay);

        System.out.println("2年后日期：" + localDate.plusYears(2));
        System.out.println("6月后日期：" + localDate.plusMonths(6));
        System.out.println("3周后日期：" + localDate.plusWeeks(3));
        System.out.println("15天后日期：" + localDate.plusDays(15));

        System.out.println("2年前日期：" + localDate.minusYears(2));
        System.out.println("6月前日期：" + localDate.minusMonths(6));
        System.out.println("3周前日期：" + localDate.minusWeeks(3));
        System.out.println("15天前日期：" + localDate.minusDays(15));
    }

    public static void testDateInterval() {
        //获取某年份的第N天的日期
        LocalDate specialDay = LocalDate.ofYearDay(2020, 100);
        System.out.println("2020年的第100天：" + specialDay);
        //获取两个日期的间隔天数
        long intervalDay = LocalDate.now().toEpochDay() - specialDay.toEpochDay();
        System.out.println("间隔天数： " + intervalDay);
    }
}
