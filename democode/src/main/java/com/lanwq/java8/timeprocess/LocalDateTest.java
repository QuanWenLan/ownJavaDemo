package com.lanwq.java8.timeprocess;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

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
        Date date = new Date();
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println(localDateTime1);
        System.out.println(localDateTime1.toLocalDate());
        // 计算两个日期之间相差的天数 https://blog.csdn.net/zls_1029/article/details/82930583?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1.control
        Date date1 = new Date(199, 11, 31);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date1);
        LocalDate of = LocalDate.of(instance.get(Calendar.YEAR), instance.get(Calendar.MONTH) + 1, instance.get(Calendar.DAY_OF_MONTH));
        System.out.println("of:::" + of.toString());
        LocalDate of2 = LocalDate.of(instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), 21);
        System.out.println("of:::" + of2.toString());
        System.out.println(of.toEpochDay() - of2.toEpochDay());
        System.out.println(of.until(of2, ChronoUnit.DAYS));

        testConvertDateToLocalDate();
        testConvertLocalDateToDate();
    }

    private static void testConvertDateToLocalDate() {
        System.out.println("Date to LocalDate");
        /**
         * 1）将java.com.lanwq.other.util.Date转换为ZonedDateTime。
         * 2）使用它的toLocalDate（）方法从ZonedDateTime获取LocalDate。
         */
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        System.out.println("Date = " + date);
        System.out.println("LocalDate = " + localDate);
    }

    private static void testConvertLocalDateToDate() {
        /**
         * 1）使用ZonedDateTime将LocalDate转换为Instant。
         * 2）使用from（）方法从Instant对象获取Date的实例
         */
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);

        Date date = Date.from(zdt.toInstant());

        System.out.println("LocalDate = " + localDate);
        System.out.println("Date = " + date);
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
