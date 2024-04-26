package org.geekbang.time.commonmistakes.numeralcalculations.dangerousdouble;


import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class CommonMistakesApplication {

    public static void main(String[] args) {
        System.out.println("testScale");
        testScale();
        System.out.println("wrong1");
        wrong1();
        System.out.println("wrong2");
        wrong2();
        System.out.println("right");
        right();
    }

    private static void wrong1() {
        /*
         * 比如，0.1 的二进制表示为 0.0 0011 0011 0011… （0011 无限循环)，再转换为十进制就
         * 是 0.1000000000000000055511151231257827021181583404541015625。对于计算
         * 机而言，0.1 无法精确表达，这是浮点数计算造成精度损失的根源。
         */
        System.out.println(0.1 + 0.2); // 0.30000000000000004
        System.out.println(1.0 - 0.8); // 0.19999999999999996
        System.out.println(4.015 * 100); // 401.49999999999994
        System.out.println(123.3 / 100); // 1.2329999999999999

        double amount1 = 2.15;
        double amount2 = 1.10;

        if (amount1 - amount2 == 1.05) {
            System.out.println("OK");
        }
    }

    private static void testScale() {
        /*
        scale 表示小数点右边的位数，而 precision 表示精度，也就是有效数字的长度。
        对于 BigDecimal 乘法操作，返回值的 scale 是两个数的 scale 相加
         */
        BigDecimal bigDecimal0 = new BigDecimal("4.015");
        BigDecimal bigDecimal1 = new BigDecimal("100");
        BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(100d));
        BigDecimal bigDecimal3 = new BigDecimal(String.valueOf(100));
        BigDecimal bigDecimal4 = BigDecimal.valueOf(100d);
        BigDecimal bigDecimal5 = new BigDecimal(Double.toString(100));

        print(bigDecimal0);
        print(bigDecimal1); //scale 0 precision 3 result 401.500
        print(bigDecimal2); //scale 1 precision 4 result 401.5000
        print(bigDecimal3); //scale 0 precision 3 result 401.500
        print(bigDecimal4); //scale 1 precision 4 result 401.5000
        print(bigDecimal5); //scale 1 precision 4 result 401.5000
    }

    private static void print(BigDecimal bigDecimal) {
        log.info("bigDecimal: {}, scale {} precision {}, multiply '4.015' -> result {}", bigDecimal, bigDecimal.scale(), bigDecimal.precision(),
                bigDecimal.multiply(new BigDecimal("4.015")));
    }

    private static void wrong2() {
        // 0.3000000000000000166533453693773481063544750213623046875
        System.out.println(new BigDecimal(0.1).add(new BigDecimal(0.2)));
        // 0.1999999999999999555910790149937383830547332763671875
        System.out.println(new BigDecimal(1.0).subtract(new BigDecimal(0.8)));
        // 401.49999999999996802557689079549163579940795898437500
        System.out.println(new BigDecimal(4.015).multiply(new BigDecimal(100)));
        // 1.232999999999999971578290569595992565155029296875
        System.out.println(new BigDecimal(123.3).divide(new BigDecimal(100)));
    }

    private static void right() {
        /*
        使用 BigDecimal 表示和计算浮点数，且务必使用字符串的构造方法来初始化 BigDecimal 对象。
         */
        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
        System.out.println(new BigDecimal("1.0").subtract(new BigDecimal("0.8")));
        System.out.println(new BigDecimal("4.015").multiply(new BigDecimal("100")));
        System.out.println(new BigDecimal("123.3").divide(new BigDecimal("100")));
    }
}

