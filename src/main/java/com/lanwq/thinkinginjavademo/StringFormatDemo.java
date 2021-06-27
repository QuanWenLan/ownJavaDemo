package com.lanwq.thinkinginjavademo;

import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Vin lan
 * @className FormatDemo
 * @description TODO 字符串格式练习 ,参考学习链接：https://blog.csdn.net/lonely_fireworks/article/details/7962171
 * @createTime 2021-02-23  09:59
 **/
public class StringFormatDemo {
    public static void main(String[] args) {
/*        commonStringFormat();
        System.out.printf("===============%n");
        otherUsage();
        System.out.printf("===============%n");
        formatWithSymbol();
        System.out.printf("===============%n");
        formatWithTime();*/
        System.out.printf("%tY%010d", LocalDate.now(), 568);
    }

    /**
     * %s  字符串类型   "ming"
     * %c  字符类型   'm'
     * %b  布尔类型   true
     * %d  整数类型（十进制）   10
     * %x  整数类型（十六进制）   FF
     * %o  整数类型（八进制）   77
     * %f  浮点类型   99.99
     * %a  十六进制浮点类型   FF.35AE
     * %e  指数类型   2.5e+6
     * %g  通用浮点类型（f和e类型中较短的）
     * %h  散列码
     * %%  百分比类型
     * %n  换行符
     * %tx 日期与时间类型（x代表不同的日期与时间转换符
     */
    private static void commonStringFormat() {
        String str = null;
        str = String.format("Hi,%s", "王力");
        System.out.println(str);
        str = String.format("Hi,%s:%s.%s", "王南", "王力", "王张");
        System.out.println(str);
        System.out.printf("字母a的大写是：%c %n", 'A');
        System.out.printf("3>7的结果是：%b %n", 3 > 7);
        System.out.printf("100的一半是：%d %n", 100 / 2);
        System.out.printf("100的16进制数是：%x %n", 100);
        System.out.printf("100的8进制数是：%o %n", 100);
        System.out.printf("50元的书打8.5折扣是：%f 元%n", 50 * 0.85);
        System.out.printf("上面价格的16进制数是：%a %n", 50 * 0.85);
        System.out.printf("上面价格的指数表示：%e %n", 50 * 0.85);
        System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50 * 0.85);
        System.out.printf("上面的折扣是%d%% %n", 85);
        System.out.printf("字母A的散列码是：%h %n", 'A');
    }

    /**
     * 其他的一些使用
     */
    public static void otherUsage() {
        Stream<String> stream = Stream.of("a", "b", "c");
        String compositeString = stream.collect(Collectors.joining("', '", "'", "'"));
        System.out.println(String.format("拼接好的字符串, (%s)", compositeString));
    }

    /**
     * 搭配转换符号使用
     * +      为正数或者负数添加符号  ("%+d15", 15)      +15
     * -      左对齐                ("%-5d",15)        |15  |
     * 0      数字前面补0            ("%04d", 99)       0099
     * 空格    添加指定数量的空格      ("% 4d", 99)      |  99|
     * ，      以","对数字分组        ("%,f", 9999.99)  9,999.990000
     * (       使用括号包含负数       ("%(f", -99.99)   (99.990000)
     * #       如果是浮点数则包含小数点       ("%#x", 99) 0x63
     * 如果是16进制或8进制则添0x 或0  ("%#o", 99) 0143
     * <       格式化前一个转换符所描述的参数    ("%f和%<3.2f", 99.45)  99.450000和99.45
     * <p>
     * $        被格式化的参数索引             ("%1$d,%2$s", 99,"abc")  99,abc
     */
    public static void formatWithSymbol() {
        String str = null;
        //$使用
        str = String.format("格式参数$的使用：%1$d,%2$s", 99, "abc");
        System.out.println(str);
        //+使用
        System.out.printf("显示正负数的符号：%+d与%d%n", 99, -99);
        //补O使用
        System.out.printf("最牛的编号是：%03d%n", 7);
        //空格使用
        System.out.printf("Tab键的效果是：% 8d%n", 7);
        //.使用
        System.out.printf("整数分组的效果是：%,d%n", 9989997);
        //空格和小数点后面个数
        System.out.printf("一本书的价格是：% 50.5f元%n", 49.8);
        // 左对齐
        System.out.printf("左边空格5个%-5d，右边一个%n", 5);
    }

    /**
     * 字符串格式和时间相使用，使用 %tx, x代表另外的处理日期和时间格式的转换符
     * c       包括全部时间和时间信息  星期五 三月 12 15:53:41 CST 2021
     * F       “年-月-日”格式  2021-03-12
     * D       “月/日/年”格式  03/12/21
     * r       “HH:MM:SS PM”格式（12小时制） 03:53:41 下午
     * T       “HH:MM:SS”格式（24小时制）  15:53:41
     * R       “HH:MM”格式（24小时制）  15:53
     */
    public static void formatWithTime() {
        Date date = new Date();
        // c 的使用。
        System.out.printf("全部时间和时间信息：%tc%n", date);
        // F 的使用
        System.out.printf("年-月-日格式：%tF%n", date);
        // D的使用
        System.out.printf("月/日/年格式：%tD%n", date);
        // r的使用
        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n", date);
        // t的使用
        System.out.printf("HH:MM:SS格式（24时制）：%tT%n", date);
        // R的使用
        System.out.printf("HH:MM格式（24时制）：%tR", date);
    }
}
