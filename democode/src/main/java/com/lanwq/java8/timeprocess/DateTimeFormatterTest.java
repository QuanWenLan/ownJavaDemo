package com.lanwq.java8.timeprocess;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * @author Lan
 * @createTime 2024-04-08  16:40
 **/
public class DateTimeFormatterTest {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }


    public static void test1() {
        // 时间转字符串
        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("s1 = " + s1);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("s2 = " + s2);
    }

    public static void test2() {
        // 字符串时间转时间
        LocalDate date1 = LocalDate.parse("20140318",
                DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("date1 = " + date1);
        LocalDate date2 = LocalDate.parse("2014-03-18",
                DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("date2 = " + date2);
    }

    public static void test3() {
        //按照某个模式创建DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date3 = LocalDate.of(2014, 3, 18);
        System.out.println("date3 = " + date3);
        String formattedDate = date3.format(formatter);
        LocalDate date4 = LocalDate.parse(formattedDate, formatter);
        System.out.println("date4 = " + date4);
    }

    public static void test4() {
        // 创建一个本地化的DateTimeFormatter
        DateTimeFormatter italianFormatter =
                DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        LocalDate date = LocalDate.of(2014, 3, 18);
        String formattedDate = date.format(italianFormatter); // 18. marzo 2014
        System.out.println("formattedDate = " + formattedDate);
        LocalDate date2 = LocalDate.parse(formattedDate, italianFormatter);
        System.out.println("date2 = " + date2);
    }

    public static void test5() {
        // 构建自定义formatter
        DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);
    }
}
