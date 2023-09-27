package com.lanwq.thinkinginjavademo.fourteenthclassrtti;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * @author Vin lan
 * @className Test
 * @description TODO
 * @createTime 2020-10-14  15:32
 **/
public class Test {
    public static void main(String[] args) {
        int i = 1;
        int n = (++i) + (++i);
        System.out.println(n);

        DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY-MM-dd");
        DateTime requestDateTime = formatter.parseDateTime("2020-10-14"); // throws RuntimeException
        boolean isCurrentDate =
                new DateTime().withTimeAtStartOfDay().isEqual(requestDateTime.withTimeAtStartOfDay());
        System.out.println(isCurrentDate);
        System.out.println(requestDateTime.withTimeAtStartOfDay());

        System.out.println(Double.compare(12.12, 10.10));

        int num1 = 7;

        int num2 = 9;

        // 创建一个数值格式化对象

        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat = NumberFormat.getNumberInstance();

        // 设置精确到小数点后2位

        numberFormat.setMaximumFractionDigits(2);

        String result = numberFormat.format((float) num1 / (float) num2 * 100);

        System.out.println("num1和num2的百分比为:" + result + "%");
        System.out.println("num1和num2的百分比为:" + numberFormat.format( (18.74 - 18.06) / 18.74 * 100) + "%");
        System.out.println("num1和num2的百分比为:" + Double.parseDouble(numberFormat.format( (18.74 - 18.06) / 18.74 * 100)) + "%");
        try {
            Number parse = numberFormat.parse(numberFormat.format((18.74 - 18.06) / 18.74 * 100));
            System.out.println("num1和num2的百分比为:" + (double)parse + "%");
            System.out.println(Double.parseDouble(18.12 / 0.01 * 100 + ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
