package com.lanwq.other;

/**
 * @author Vin lan
 * @className DigitalNumberConvertToEnglishNumber
 * @description TODO 数字金额转化为英文金额
 * @createTime 2020-11-16  10:49
 * 源代码：@see<a href="http://blog.csdn.net/l1028386804/article/details/52599090">http://blog.csdn.net/l1028386804/article/details/52599090</a>
 **/
public class DigitalNumberConvertToEnglishNumber {

    public static void main(String[] args) {
        /*System.out.println(NumberParser.parse("0.0"));
        System.out.println(NumberParser.parse("5.0"));
        System.out.println(NumberParser.parse("1234567.023"));*/
        System.out.println(Double.parseDouble("0"));
        System.out.println(0.0 == 0.0);
    }

    //10以内的数字
    private static final String[] SINGLE_NUM_ARR = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    //十几的数字
    private static final String[] TEN_NUM_ARR = new String[]{"Ten", "Eleven", "Tweleve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    //整十的数字
    private static final String[] TEN_INTEGER_ARR = new String[]{"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public static String parse(String x) {
        if (Double.parseDouble(x) <= 0) {
            return "Zero Cents only";
        }
        int z = x.indexOf("."); // 取小数点位置
        String lstr = "", rstr = "";
        if (z > -1) { // 看是否有小数，如果有，则分别取左边和右边
            lstr = x.substring(0, z);
            rstr = x.substring(z + 1);
        } else // 否则就是全部
            lstr = x;

        String lstrrev = reverse(lstr); // 对左边的字串取反
        String[] a = new String[5]; // 定义5个字串变量来存放解析出来的叁位一组的字串

        switch (lstrrev.length() % 3) {
            case 1:
                lstrrev += "00";
                break;
            case 2:
                lstrrev += "0";
                break;
        }
        String lm = ""; // 用来存放转换後的整数部分
        for (int i = 0; i < lstrrev.length() / 3; i++) {
            a[i] = reverse(lstrrev.substring(3 * i, 3 * i + 3)); // 截取第一个叁位
            if (!a[i].equals("000")) { // 用来避免这种情况：1000000 = one million
                if (i != 0)
                    lm = transThree(a[i]) + " " + parseMore(String.valueOf(i)) + " " + lm; // 加:
                    // thousand、million、billion
                else
                    lm = transThree(a[i]); // 防止i=0时， 在多加两个空格.
            } else
                lm += transThree(a[i]);
        }

        String xs = ""; // 用来存放转换後小数部分
        if (z > -1) {
            String transTwo = transTwo(rstr);
            if (transTwo == null || "".equals(transTwo)) {
                xs = "";
            } else {
                xs = "and " + transTwo + " Cents "; // 小数部分存在时转换小数
            }
        }
        return lm.trim() + " " + xs + "only";
    }

    private static String parseFirst(String s) {
        //String[] a = new String[] { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
        return SINGLE_NUM_ARR[Integer.parseInt(s.substring(s.length() - 1))];
    }

    private static String parseTeen(String s) {
        //String[] a = new String[] { "Ten", "Eleven", "Tweleve", "Thirteen", "Fourteen", "Fifteen", "Sixteen","Seventeen", "Eighteen", "Nineteen" };
        return TEN_NUM_ARR[Integer.parseInt(s) - 10];
    }

    private static String parseTen(String s) {
        //String[] a = new String[] { "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
        return TEN_INTEGER_ARR[Integer.parseInt(s.substring(0, 1)) - 1];
    }

    // 两位
    private static String transTwo(String s) {
        String value = "";
        // 判断位数
        if (s.length() > 2)
            s = s.substring(0, 2);
        else if (s.length() < 2)
            s = s + "0";

        if (s.startsWith("0")) // 07 - seven 是否小於10
            value = parseFirst(s);
        else if (s.startsWith("1")) // 17 seventeen 是否在10和20之间
            value = parseTeen(s);
        else if (s.endsWith("0")) // 是否在10与100之间的能被10整除的数
            value = parseTen(s);
        else
            value = parseTen(s) + " " + parseFirst(s);
        return value;
    }

    private static String parseMore(String s) {
        String[] a = new String[]{"", "Thousand", "Million", "Billion"};
        return a[Integer.parseInt(s)];
    }

    // 制作叁位的数
    // s.length = 3
    private static String transThree(String s) {
        String value = "";
        if (s.startsWith("0")) // 是否小於100
            value = transTwo(s.substring(1));
        else if (s.substring(1).equals("00")) // 是否被100整除
            value = parseFirst(s.substring(0, 1)) + " Hundred";
        else
            value = parseFirst(s.substring(0, 1)) + " Hundred and " + transTwo(s.substring(1));
        return value;
    }

    private static String reverse(String s) {
        char[] aChr = s.toCharArray();
        StringBuffer tmp = new StringBuffer();
        for (int i = aChr.length - 1; i >= 0; i--) {
            tmp.append(aChr[i]);
        }
        return tmp.toString();
    }
}
