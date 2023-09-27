package timeprocess;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Vin lan
 * @className TimeZoneTest
 * @description TODO https://blog.csdn.net/earthchinagl/article/details/71404061
 * @createTime 2021-03-05  12:27
 * <p>
 * TimeZone 表示 时区偏移量，也可以记作 夏令时
 **/
public class TimeZoneTest {

    // 1 获取时区的第一种方法
    @Test
    public void getTimeZone() {
        TimeZone defaultTimeZone = TimeZone.getDefault();
        System.out.println(defaultTimeZone);
    }

    // 2 获取时区的第二种方法
    @Test
    public void testBigDecimal() {
        // 获取 “GMT+08:00”对应的时区
        TimeZone china = TimeZone.getTimeZone("GMT+:08:00");
        System.out.println(china);
        // 获取 “中国/重庆”对应的时区
        TimeZone chongqing = TimeZone.getTimeZone("Asia/Chongqing");
        System.out.println(chongqing);
    }

    // 获取所有的可用时区
    @Test
    public void getAllTheTimeZoneIds() {
        String[] availableIDs = TimeZone.getAvailableIDs();
        for (String id :
                availableIDs) {
            System.out.println("时区ID：" + id);
        }
    }

    // 一起其他的一些API
    @Test
    public void testOtherApi() {
        TimeZone tz = TimeZone.getDefault();

        String id = tz.getID();

        String displayName = tz.getDisplayName();

        // 获取“时间偏移”。相对于“本初子午线”的偏移，单位是ms。
        int offset = tz.getRawOffset();
        // 获取“时间偏移” 对应的小时
        int gmt = offset / (3600 * 1000);
        System.out.printf("id=%s, name=%s, offset=%s(ms), gmt=%s\n",
                id, displayName, offset, gmt);
    }

    @Test
    public void test1() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date parse = simpleDateFormat.parse("20110325000000");
        System.out.println(parse.toString());
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMdd");
        Date parse2 = simpleDateFormat2.parse("20110325000000");
        System.out.println(parse2);
    }
}
/*
* 一些注意点：TimeZone.getDefault();  这个方法最终会调用
*  // get the time zone ID from the system properties
* String zoneID = AccessController.doPrivileged(new GetPropertyAction("user.timezone"));
* 获取的是这个系统属性 user.timezone
* 可以对其进行修改来设置JVM的一个统一的 user.timezone
* System.setProperty("user.timezone","Asia/Shanghai"); 或者指定JVM的参数：java -Duser.timezone=Asia/Shanghai DateTest
 * */