package com.lanwq.thinkinginjavademo.controllingexecuting;

import java.util.Arrays;

/**
 * @ClassName XiXueGui
 * @Description TODO 实现吸血鬼数字
 * 是指位数为偶数的数字，可以由一对数字相乘得到，而这对数字各包含乘积的一半位数的数字，
 * 其中从最初的数字中选取的数字可以任意排序，两个以 0 结尾的数字是不允许的。例如：
 * 1260 = 21 * 60
 * 1827 = 21 * 87   /10=>182 ...7 ,/100=>18 ...27
 * 2187 = 27 * 81
 * @Author Administrator
 * @Date 2019/6/23  17:20
 * @Version 1.0
 */
public class XiXueGui {
    public static void main(String[] args) {
//        xiXueGui01();
        xiXueGui02();
    }

    private static void xiXueGui02() {
        for (int i = 10; i < 100; i++) {
            for (int j = i + 1; j < 100; j++) {
                int target = i * j;
                if (target < 1000 || target > 9999) {
                    continue;
                }
                int[] tarNum = {target / 1000, (target / 100) % 10, (target / 10) % 10, target % 10};
                int[] strNum = {i % 10, i / 10, j % 10, j / 10};
                Arrays.sort(tarNum);
                Arrays.sort(strNum);
                if (Arrays.equals(tarNum, strNum)) {
                    System.out.println(target + " = " + i + " * " + j);
                }
            }
        }
    }

    private static void xiXueGui01() {
        int a, b, c, d;  // 个十百千
        for (int i = 1001; i < 9999; i++) {
            a = i % 10;
            b = (i / 10) % 10;
            c = (i / 100) % 10;
            d = i / 1000;

            getNum(i, getCon(a, b), getCon(c, d)); // ab cd
            getNum(i, getCon(a, b), getCon(d, c)); // ab dc
            getNum(i, getCon(a, c), getCon(b, d)); // ac bd
            getNum(i, getCon(a, c), getCon(d, b)); // ac db
            getNum(i, getCon(a, d), getCon(b, c)); // ad bc
            getNum(i, getCon(a, d), getCon(c, b)); // ad cb

            getNum(i, getCon(b, a), getCon(c, d)); // ba cd
            getNum(i, getCon(b, a), getCon(d, c)); // ba dc
            getNum(i, getCon(b, c), getCon(d, a)); // bc da
            getNum(i, getCon(b, c), getCon(a, d)); // bc ad
            getNum(i, getCon(b, d), getCon(c, a)); // bd ca
            getNum(i, getCon(b, d), getCon(a, c)); // bd ac

            getNum(i, getCon(c, a), getCon(d, b)); // ca db
//            getNum(i, getCon(c, a), getCon(b, d)); // ca bd
//            getNum(i, getCon(c, b), getCon(a, d));//cb ad
            getNum(i, getCon(c, b), getCon(d, a)); // cb da
//            getNum(i, getCon(c, d), getCon(b, a)); // cd ba
//            getNum(i, getCon(c, d), getCon(a, b)); // cd ab

//            getNum(i, getCon(d, a), getCon(c, b));// da cb
//            getNum(i, getCon(d, a), getCon(b, c));  // da bc
//            getNum(i, getCon(d, b), getCon(a, c));  // db ac
//            getNum(i, getCon(d, b), getCon(c, a));// db ca
//            getNum(i, getCon(d, c), getCon(b, a)); //  dc ba
//            getNum(i, getCon(d, c), getCon(a, b)); //  dc ab
        }

    }

    private static void getNum(int i, int start, int end) {
        int result = start * end;
        if (i == result) {
            System.out.println(i + "=" + start + "*" + end);
        }
    }

    private static int getCon(int a, int b) {
        return a * 10 + b;
    }
}
