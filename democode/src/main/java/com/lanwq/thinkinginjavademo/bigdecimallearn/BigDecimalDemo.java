package com.lanwq.thinkinginjavademo.bigdecimallearn;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Vin lan
 * @className BigDecimalDemo
 * @description BigDecimal 使用注意事项,可参见一下链接
 * <a href="https://www.cnblogs.com/panchanggui/p/10766607.html">https://www.cnblogs.com/panchanggui/p/10766607.html</a>
 * @createTime 2021-03-12  16:01
 **/
public class BigDecimalDemo {
    // 计算金额的时候不能使用double或者是float，要使用 BigDecimal，金额是一个精确的数字
    // MySQL数据库中数据类型，NUMERIC和DECIMAL都对应这个 BigDecimal这个java类
    public static void main(String[] args) {
        useDoubleToCalculate();
        System.out.println("==========");
        createBigDecimal();
        System.out.println("==========");
        useBigDecimalToCalculate();

    }

    /**
     * 使用double进行计算会遗失一些数据，丢失了数据的精度，要参考double的数据类型，是8个字节，64位
     * 为何会这样？网上查找资料看看，还未去查。网上搜索：float型和double型数据的存储方式
     * 参考这篇：https://cloud.tencent.com/developer/article/1473541
     * double 类型的数据进行加减等操作，会转换成2进制来进行计算，因为Double有效位数为16位这就会出现存储小数位数不够的情况，这种情况下就会出现误差。
     *
     */
    public static void useDoubleToCalculate() {
        double a = 0.02; // 小数的默认类型, 双精度浮点型，精度：科学记数法的小数点后15~16位
        double b = 0.03;
        double c = b - a;
        System.out.println(c); // output != 0.01， 而是 0.009999999999999998

        float d = 0.02f; // 单精度浮点型，精度：科学记数法的小数点后6~7位
        float e = 0.03f;
        float f = e - d;
        System.out.println(f); // output = 0.01
    }

    // 使用 BigDecimal 来计算

    // 创建 BigDecimal
    public static void createBigDecimal() {
        BigDecimal a = new BigDecimal(0.02);
        System.out.println("a = " + a);
        BigDecimal b = BigDecimal.valueOf(0.03); // 推荐使用这种来进行创建
        System.out.println("b = " + b);
        // 使用String来进行创建
        BigDecimal c = new BigDecimal("0.02");
        System.out.println("c = " + c);
        // 或者可以使用 String.valueOf() 进行包装来创建
        BigDecimal d = new BigDecimal(String.valueOf(0.02));
        System.out.println("d = " + d);
        // 以上的输出结果有差别
        /**
         a = 0.0200000000000000004163336342344337026588618755340576171875 使用的是double的构造函数创建的
         b = 0.03
         c = 0.02
         d = 0.02
         */
    }

    // 加减乘除，大小比较，四则运算
    public static void useBigDecimalToCalculate() {
        BigDecimal a = BigDecimal.valueOf(0.03);
        BigDecimal b = BigDecimal.valueOf(0.02);
        // 加
        BigDecimal c = a.add(b);  // a 加数， b 被加数
        System.out.println("a= " + a + ", c = " + c); // a= 0.02, c = 0.04, a，b都不会变，返回的是一个新的BigDecimal
        // 减
        BigDecimal d = a.subtract(b);
        System.out.println("a= " + a + ", d = " + d); // a= 0.02, d = 0.00, a，b都不会变，返回的是一个新的BigDecimal
        // 乘
        BigDecimal e = a.multiply(b);
        System.out.println("a= " + a + ", e = " + e); // a= 0.02, e = 0.0004, a，b都不会变，返回的是一个新的BigDecimal
        // 除
        BigDecimal f = a.divide(b, RoundingMode.HALF_UP); // 取值范围，四舍五入
        System.out.println("a= " + a + ", f = " + f); // a= 0.02, f = 1.00, a，b都不会变，返回的是一个新的BigDecimal

        // 大小比较，不能使用 >, <, !=, ==
        if (a.compareTo(BigDecimal.ZERO) > 0) { // 相等
            System.out.println("a 大于 0");
        } else if (a.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("a 小于 0");
        } else if (a.compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("a 等于 0");
        }

        // 四舍五入  RoundingMode.HALF_UP, 参考源码中的文档
        BigDecimal g = BigDecimal.valueOf(0.0236);
        System.out.println(g.setScale(2, RoundingMode.HALF_UP)); // 保留两位，四舍五入

    }
}
