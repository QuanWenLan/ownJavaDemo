package com.lanwq.java8.timeprocess;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Vin lan
 * @className SimpleDateFormatTest
 * @description TODO
 * @createTime 2020-10-29  15:27
 **/
public class SimpleDateFormatTest {
    public static void main(String[] args) {
        String format = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        System.out.println(format);
    }
}
