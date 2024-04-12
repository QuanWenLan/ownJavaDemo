package com.lanwq.java8.timeprocess;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.TimeZone;

/**
 * @author Lan
 * @createTime 2024-04-08  16:46
 **/
public class ZoneIdTest {
    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        // 地区ID都为“{区域}/{城市}”的格式，这些地区集合的设定都由英特网编号分配机构（IANA）的时区数据库提供。
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        System.out.println("romeZone = " + romeZone);
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        System.out.println("default zone: " + zoneId);

        // 为时间添加时区信息
        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        System.out.println("dateTime = " + dateTime);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);
        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);
    }

    /**
     * ZonedDateTime 类在LocalDateTime的基础上添加了时区信息，它表示一个在特定时区中的日期和时间。
     * 组成
     * 2014-05-14    T15:33:05.941+01:00[Europe/London]
     * LocalDate-----LocalTime-------ZoneId
     * -----LocalDateTime-----
     * -----------ZonedDateTime------------
     */
    public static void test2() {
        // 通过ZoneId，你还可以将LocalDateTime转换为Instant：
        LocalDateTime dateTime2 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        Instant instantFromDateTime = dateTime2.toInstant(ZoneOffset.UTC);
        System.out.println("instantFromDateTime = " + instantFromDateTime);
        // instant 转换为  LocalDateTime
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        LocalDateTime dateTime3 = LocalDateTime.ofInstant(Instant.now(), romeZone);
        System.out.println("dateTime3 = " + dateTime3);
    }

    /**
     * 另一种比较通用的表达时区的方式是利用当前时区和UTC/格林尼治的固定偏差。比如，基于这个理论，你可以说“纽约落后于伦敦5小时”。这种情况下，
     * 你可以使用ZoneOffset类，它是ZoneId的一个子类，表示的是当前时间和伦敦格林尼治子午线时间的差异：
     * "-05:00”的偏差实际上对应的是美国东部标准时间。注意，使用这种方式定义的ZoneOffset
     * 并未考虑任何日光时的影响，所以在大多数情况下，不推荐使用.
     * 由于ZoneOffset也是ZoneId，
     * 所以你可以像代码清单12-13那样使用它。你甚至还可以创建这样的OffsetDateTime，它使用
     * ISO-8601的历法系统，以相对于UTC/格林尼治时间的偏差方式表示日期时间
     */
    public static void test3() {
        ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");

        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(LocalDateTime.now(), newYorkOffset);
    }
}
