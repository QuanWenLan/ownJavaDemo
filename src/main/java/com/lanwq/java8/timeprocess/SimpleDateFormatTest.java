package com.lanwq.java8.timeprocess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Vin lan
 * @className SimpleDateFormatTest
 * @description TODO
 * @createTime 2020-10-29  15:27
 **/
public class SimpleDateFormatTest {
    public static void main(String[] args) throws ParseException {
        Date currentDate = new Date();
        // 时间格式 MM/dd/yyyy
        String format = new SimpleDateFormat("MM/dd/yyyy").format(currentDate);
        System.out.println(format);
        System.out.println(currentDate.toString().split(" ")[1]);

        // 获取英文的月份，也是根据具体的pattern来的 ，具体的可以参考：https://blog.csdn.net/KingWTD/article/details/48089111
        SimpleDateFormat format2 = new SimpleDateFormat("MMM dd", Locale.ENGLISH);
        System.out.println(format2.format(currentDate));

        SimpleDateFormat format3 = new SimpleDateFormat("MMM yyy", Locale.ENGLISH);
        System.out.println(format3.format(currentDate));

        SimpleDateFormat format4 = new SimpleDateFormat("MMyy");
        System.out.println(format4.format(currentDate));
        // 2018-11-14
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        Date parseDate = simpleDateFormat.parse("2018-12-14");
        System.out.println(parseDate);

    }

    public void formatGmtTime() {
        // 时间格式，使用play框架中的form表单来弄的，前端传过来的是形如 202002200000 的字符串，在通过以下注解转换成了timezone为 “GMT+8” 的时间格式
        /*
        import com.fasterxml.jackson.annotation.JsonFormat;
        import play.data.format.Formats;

        @Formats.DateTime(pattern = "yyyyMMddHHmmss")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "GMT+8")
        Date orderDateFrom;

        @Formats.DateTime(pattern = "yyyyMMddHHmmss")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "GMT+8")
        Date orderDateTo;*/
        // 然后再使用以下格式化成相对应的时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of("GMT+8")));
    }

}
