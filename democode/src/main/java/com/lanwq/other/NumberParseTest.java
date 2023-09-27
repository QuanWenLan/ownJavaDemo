package com.lanwq.other;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * @author Vin lan
 * @className NumberParseTest
 * @description 数字格式化, 最主要使用 @Link{java.text.DecimalFormat} 这个类，
 * 参考链接：格式化数字、货币、日期、时间、字符串信息：https://docs.oracle.com/javase/tutorial/i18n/format/index.html
 * @createTime 2021-06-29  09:37
 **/
public class NumberParseTest {
    public static void main(String[] args) {
        test1();

        test2();

        NumberFormat.getIntegerInstance();
    }

    /**
     * 要格式化数字的时候记得使用 NumberFormat 这个类  #,##0.###;(#,##0.###-)
     */
    public static void test1() {
        Locale[] locales = NumberFormat.getAvailableLocales();
        double myNumber = -1234.56;
        NumberFormat form;
        for (int j = 0; j < 4; ++j) {
            System.out.println("FORMAT");
            for (int i = 0; i < locales.length; ++i) {
                if (locales[i].getCountry().length() == 0) {
                    continue; // Skip language-only locales
                }
                System.out.print(locales[i].getDisplayName());
                switch (j) {
                    case 0:
                        form = NumberFormat.getInstance(locales[i]);
                        break;
                    case 1:
                        form = NumberFormat.getIntegerInstance(locales[i]);
                        break;
                    case 2:
                        form = NumberFormat.getCurrencyInstance(locales[i]);
                        break;
                    default:
                        form = NumberFormat.getPercentInstance(locales[i]);
                        break;
                }
                if (form instanceof DecimalFormat) {
                    System.out.print(": " + ((DecimalFormat) form).toPattern());
                }
                System.out.print(" -> " + form.format(myNumber));
                try {
                    System.out.println(" -> " + form.parse(form.format(myNumber)));
                } catch (ParseException e) {
                }
            }
        }
    }

    /**
     * 保留小数点后两位
     */
    public static void test2() {
        double f = 111231.5585;
        System.out.println("保留两位小数：" + f);
        // （1）使用string的格式化
        System.out.println(String.format("%.2f", f));
        // （2）使用 decimalFormat
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(f));
        // （3）BigDecimal的setScale方法
        BigDecimal bg = new BigDecimal(f);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1);
        // （4）NumberFormat的setMaximumFractionDigits方法
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        System.out.println(nf.format(f));
    }
}
