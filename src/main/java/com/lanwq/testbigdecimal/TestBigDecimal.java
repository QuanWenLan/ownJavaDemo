package com.lanwq.testbigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @program: javaDemo->TestBigdecimal
 * @description:
 * @author: lanwenquan
 * @date: 2020-12-16 22:03
 */
public class TestBigDecimal {
    public static void main(String[] args) {
        testConstructor();
        compute();
        compare();
        format();
        cycle();
        scientificNotation();
        testConst();
    }

    /**
     * 构造函数的问题
     */
    public static void testConstructor() {
        BigDecimal a = new BigDecimal(0.1);
        System.out.println("使用 数值 0.1 构造, a values is:" + a);
        System.out.println("=====================");
        BigDecimal b = new BigDecimal("0.1");
        System.out.println("使用 字符串 '0.1' 构造，b values is:" + b);
        // 输出

    }

    /**
     * 常用计算方法
     */
    public static void compute() {
        BigDecimal num1 = new BigDecimal(0.005);
        BigDecimal num2 = new BigDecimal(1000000);
        BigDecimal num3 = new BigDecimal(-1000000);
        //尽量用字符串的形式初始化
        BigDecimal num12 = new BigDecimal("0.005");
        BigDecimal num22 = new BigDecimal("1000000");
        BigDecimal num32 = new BigDecimal("-1000000");

        //加法
        BigDecimal result1 = num1.add(num2);
        BigDecimal result12 = num12.add(num22);
        System.out.println("加法用value结果：" + result1);
        System.out.println("加法用string结果：" + result12);
        //减法
        BigDecimal result2 = num1.subtract(num2);
        BigDecimal result22 = num12.subtract(num22);
        System.out.println("减法value结果：" + result2);
        System.out.println("减法用string结果：" + result22);
        //乘法
        BigDecimal result3 = num1.multiply(num2);
        BigDecimal result32 = num12.multiply(num22);
        System.out.println("乘法用value结果：" + result3);
        System.out.println("乘法用string结果：" + result32);
        //绝对值
        BigDecimal result4 = num3.abs();
        BigDecimal result42 = num32.abs();
        System.out.println("绝对值用value结果：" + result4);
        System.out.println("绝对值用string结果：" + result42);
        //除法
        BigDecimal result5 = num2.divide(num1, 20, BigDecimal.ROUND_HALF_UP);
        BigDecimal result52 = num22.divide(num12, 20, BigDecimal.ROUND_HALF_UP);
        System.out.println("除法用value结果：" + result5);
        System.out.println("除法用string结果：" + result52);
    }

    public static void compare() {
        BigDecimal num1 = new BigDecimal(0.005);
        BigDecimal num2 = new BigDecimal(1000000);
        System.out.println("0.005 > 10000 ? :" + num1.compareTo(num2));
        /**
         * num1.compareTo(num2)
         * a = -1,num1 < num2
         * a = 0,num1 > num2
         * a = 1,num1 == num2
         */
    }

    public static void format() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();//建立货币格式化引用
        NumberFormat percent = NumberFormat.getPercentInstance();//建立百分比格式化引用
        percent.setMaximumFractionDigits(3);//百分比小数点最多3位

        BigDecimal loanAmount = new BigDecimal("15000.48");//贷款金额
        BigDecimal interestRate = new BigDecimal("0.008");//利率
        BigDecimal interest = loanAmount.multiply(interestRate);//相乘

        System.out.println("贷款金额:\t" + currency.format(loanAmount));
        System.out.println("利率:\t" + percent.format(interestRate));
        System.out.println("利息:\t" + currency.format(interest));

        System.out.println(formatToNumber(new BigDecimal("3.435")));
        System.out.println(formatToNumber(new BigDecimal(0)));
        System.out.println(formatToNumber(new BigDecimal("0.00")));
        System.out.println(formatToNumber(new BigDecimal("0.001")));
        System.out.println(formatToNumber(new BigDecimal("0.006")));
        System.out.println(formatToNumber(new BigDecimal("0.206")));
    }

    /**
     * @param obj 传入的小数
     * @return
     * @desc 1.0~1之间的BigDecimal小数，格式化后失去前面的0,则前面直接加上0。
     * 2.传入的参数等于0，则直接返回字符串"0.00"
     * 3.大于1的小数，直接格式化返回字符串
     */
    public static String formatToNumber(BigDecimal obj) {
        DecimalFormat df = new DecimalFormat("#.00");
        if (obj.compareTo(BigDecimal.ZERO) == 0) {
            return "0.00";
        } else if (obj.compareTo(BigDecimal.ZERO) > 0 && obj.compareTo(new BigDecimal(1)) < 0) {
            return "0" + df.format(obj);
        } else {
            return df.format(obj);
        }
    }

    /**
     * 无限循环小数，除法问题
     */
    public static void cycle() {
        // 含税金额
        BigDecimal inclusiveTaxAmount = new BigDecimal("1000");
        // 税率
        BigDecimal taxRate = new BigDecimal("0.13");
        // 不含税金额 = 含税金额 / (1+税率) : 这里会有无限循环的问题
//        BigDecimal exclusiveTaxAmount = inclusiveTaxAmount.divide(BigDecimal.ONE.add(taxRate));
//        System.out.println(exclusiveTaxAmount);

        // 解决方案是指定下舍入模式，比如我们最常用的四舍五入模式：
        // 不含税金额 = 含税金额 / (1+税率)
        BigDecimal exclusiveTaxAmount2 = inclusiveTaxAmount.divide(BigDecimal.ONE.add(taxRate), RoundingMode.HALF_UP);
        System.out.println("1000/1.13 四舍五入不保留小数:" + exclusiveTaxAmount2);
        // 不含税金额 = 含税金额 / (1+税率)， 设置两位小数
        BigDecimal exclusiveTaxAmount3 = inclusiveTaxAmount.divide(BigDecimal.ONE.add(taxRate), 2, RoundingMode.HALF_UP);
        System.out.println("1000/1.13 四舍五入保留2位小数:" + exclusiveTaxAmount3);
    }

    /**
     * 科学计数法
     * 结论：将BigDecimal转换为String，推荐使用toPlainString()，而不是toString()
     */
    public static void scientificNotation() {
        BigDecimal amount = new BigDecimal("3450.67");
        System.out.println(amount);

        System.out.println(new BigDecimal("0.000000000000"));
        //输出结果：0E-12

        BigDecimal bigDecimal = new BigDecimal("1E+11");
        System.out.println(bigDecimal);
        //输出结果：1E+11
        BigDecimal bigDecimal2 = new BigDecimal("3550.00");
        System.out.println(bigDecimal2.stripTrailingZeros());
        //输出结果：3.55E+3

        // 去除后面的0
        BigDecimal num22 = new BigDecimal("1000000.0000000000");
        System.out.println("1000000.0000000000 去除尾部的0-》" + num22.stripTrailingZeros());
        //使用toPlainString()方法可以避免这个问题，如下所示：
        System.out.println(new BigDecimal("0.000000000000").toPlainString());
        System.out.println(new BigDecimal("1E+11").toPlainString());
        System.out.println(new BigDecimal("3550.00").stripTrailingZeros().toPlainString());
        //输出结果：
        //0.000000000000
        //100000000000
        //3550

        /*toString() 某些场景下使用科学计数法
        toPlainString() 不使用任何计数法
        toEngineeringString() 某些场景下使用工程计数法*/
    }

    /**
     * BigDecimal 内部提供了一些内置的常量
     */
    public static void testConst() {
        System.out.println("BigDecimal.ZERO = " + BigDecimal.ZERO);
        System.out.println("BigDecimal.ONE = " + BigDecimal.ONE);
        System.out.println("BigDecimal.TEN = " + BigDecimal.TEN);
    }
}
