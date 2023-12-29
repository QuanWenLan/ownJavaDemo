package temptest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @author Vin lan
 * @className Testttt
 * @description
 * @createTime 2021-12-16  16:21
 **/
public class Testttt {
    @Test
    public void testInitSql() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:initSqlDataSource.xml");
        InitSqlUtil initSqlUtil = (InitSqlUtil) context.getBean("initSqlUtil");
        initSqlUtil.updateSql();
        System.out.println("结束。。。");
    }

    @Test
    public void test() {
        double a = -1.0d;
        double b = 0.0d;
        double c = a / b;
        System.out.println(c);
        System.out.println(Double.isNaN(c));
    }

    @Test
    public void test2() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Vector<Integer> vector = new Vector<Integer>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            vector.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("vector进行10000000次插入操作耗时：" + (end - start) + "ms");
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("list进行100000次插入操作耗时：" + (end - start) + "ms");
    }

    @Test
    public void test3() {
        String str1 = "abc";
        String str2 = "abc";
        System.out.println("str1 == str2 ? " + (str1 == str2));
        String str3 = new String("abc");
        System.out.println("str1 == str3 ? " + (str1 == str3));
        String str4 = new String("abc");
        System.out.println("str3 == str4 ? " + (str3 == str4));

        String a = "hello2";
        final String b = "hello";
        String c = b + 2;
        System.out.println("a == c ? " + (a == c)); // b 加上 final 为true。不加为 false
        String d = "hello";
        String e = d + 2;
        System.out.println("a == e ? " + (a == e));
    }

    @Test
    public void test4() {
        HashMap columns = new HashMap();
        int i = "abc".hashCode();
        System.out.println("hashcode:" + i);
        int i2 = i >>> 16;
        System.out.println(">>> 16 位:" + i2);
        System.out.println(i ^ i2);
        columns.put("a","a");
        System.out.println(columns);

        ArrayList<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);

    }

    @Test
    public void test5() {
        LocalDateTime beforeTime = LocalDateTime.of(2022, 6, 30, 11, 30, 20);
        System.out.println(beforeTime);
        LocalDateTime nowTime = LocalDateTime.now();
        System.out.println(nowTime);
        long until = nowTime.until(beforeTime, ChronoUnit.DAYS);
        System.out.println(until);
        long until2 = beforeTime.until(nowTime, ChronoUnit.DAYS);
        System.out.println(until2);
        long between = ChronoUnit.DAYS.between(beforeTime, nowTime);
        System.out.println(between);
        System.out.println(Math.abs(between));
        Date date = new Date(121, 10, 1);
        System.out.println(date);
    }

    @Test
    public void test6() {
        boolean empty = String.valueOf(null).isEmpty();
        System.out.println(empty);
    }
}
