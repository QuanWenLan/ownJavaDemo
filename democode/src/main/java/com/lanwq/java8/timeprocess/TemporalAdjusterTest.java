package com.lanwq.java8.timeprocess;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * @author Lan
 * @createTime 2024-04-08  16:23
 * 更复杂的操作
 **/
public class TemporalAdjusterTest {
    public static void main(String[] args) {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        LocalDate date3 = date2.with(TemporalAdjusters.lastDayOfMonth());
    }
}
/*
 * dayOfWeekInMonth 创建一个新的日期，它的值为同一个月中每一周的第几天
 * firstDayOfMonth 创建一个新的日期，它的值为当月的第一天
 * firstDayOfNextMonth 创建一个新的日期，它的值为下月的第一天
 * firstDayOfNextYear 创建一个新的日期，它的值为明年的第一天
 * firstDayOfYear 创建一个新的日期，它的值为当年的第一天
 * firstInMonth 创建一个新的日期，它的值为同一个月中，第一个符合星期几要求的值
 * lastDayOfMonth 创建一个新的日期，它的值为当月的最后一天
 * lastDayOfNextMonth 创建一个新的日期，它的值为下月的最后一天
 * lastDayOfNextYear 创建一个新的日期，它的值为明年的最后一天
 * lastDayOfYear 创建一个新的日期，它的值为今年的最后一天
 * lastInMonth 创建一个新的日期，它的值为同一个月中，最后一个符合星期几要求的值
 * next/previous
 * 创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星期几要求的日期
 * nextOrSame/previousOrSame
 * 创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星期几要求的日期，如果该日期已经符合要求，直接返回该对象
 */
