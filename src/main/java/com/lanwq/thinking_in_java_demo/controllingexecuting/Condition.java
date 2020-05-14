package com.lanwq.thinking_in_java_demo.controllingexecuting;

import java.util.Random;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;
import static net.mindview.util.Print.*;
import static net.mindview.util.Range.range;

/**
 * @ClassName Condition
 * @Description TODO 条件判断
 * @Author Administrator
 * @Date 2019/6/22  19:50
 * @Version 1.0
 */
public class Condition {
    public static void main(String[] args) {
        /*for (char c = 0; c < 128; c++){
            if(Character.isLowerCase(c)){
                System.out.println("value:" + (int)c + "  character:" + c);
            }
        }
        char c = 'A';
        System.out.println((int)c);*/
//        display();
//        suShu();
//        suShu2();
//        foreach();

//        vowelAndConstants();
        for(int i = 1; i < 10; i++){
            System.out.print(fib(i)+ ", ");
        }
    }

    private static void display() {
        int n = 0;
        Random random = new Random(48);
        while (n < 25) {
            int a = random.nextInt();
            int b = random.nextInt();
            // 紧跟随的随机值
            System.out.println("a = " + a + ", b = " + b);
            if (a < b)
                System.out.println("a < b");
            else if (a > b)
                System.out.println("a > b");
            else
                System.out.println("a = b");
            n++;
        }
    }

    /**
     * 打印 素数 的方法
     */
    private static void suShu() {
        int max = 100;
        for (int i = 1; i < max; i++) {
            boolean flag = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) flag = false;
            }
            if (flag) {
                System.out.print(i + " ");
//                Scanner sc = new Scanner(System.in);
//                sc.nextLine();
            }
        }
    }

    private static void suShu2() {
        int max = 100;
        // Get the max value from the command line,
        // if the argument has been provided:
        boolean[] sieve = new boolean[max + 1];
        int limit = (int) floor(sqrt(max));
        printnb(1 + " ");
        if (max > 1)
            printnb(2 + " ");
        // Detect prime numbers
        for (int i = 3; i <= limit; i += 2) {
            if (!sieve[i]) {
                for (int j = 2 * i; j <= max; j += i)
                    sieve[j] = true;
            }
        }
        // Print prime numbers
        for (int i = 3; i <= max; i += 2)
            if (!sieve[i]) {
                printnb(sieve[i] + " ");
                printnb(i + " ");
            }
    }

    /**
     * 展示 foreach 的语法
     */
    private static void foreach() {
        int[] range = range(10);
        for (int r : range) {
            System.out.println(r);
        }
    }

    /**
     * return 关键词 ：1. 指定方法返回什么类型的值  2. 导致当前方法的退出，并返回那个值
     * break 关键词 ：用于强制执行退出循环，不执行循环中剩余的语句
     * continue关键词： 停止执行当前的迭代，然后退回循环的起始处，开始下一次迭代
     */
    private static void label() {
//        outer:

    }

    private static void vowelAndConstants() {
        Random rand = new Random(47);
        for (int i = 0; i < 100; i++) {
            int c = rand.nextInt(26) + 'a'; // 产生一个小写字母，然后自动转型为 int 类型
            System.out.print(((char) c + " ," + c + ": "));
            switch (c) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    print("vowel");
                    break;
                case 'y':
                case 'w':
                    System.out.println("Sometimes a vowel");
                    break;
                default:
                    System.out.println("constant");
            }
        }
    }

    private static int fib(int n) {
        if (n <= 2) return 1;
        return fib(n - 1) + fib(n - 2);
    }
}
