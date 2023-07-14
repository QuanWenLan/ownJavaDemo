package com.lanwq;

/**
 * @author Lan
 * @createTime 2023-07-04  09:31
 **/
public class TryCatchTest {
    public static void main(String[] args) {
        try {
            String str = "zhuang";
        } finally {
            String name = "zhuang111111";
        }

        System.out.println("doTryFinally1 : " + doTryFinally1());
        System.out.println("doTryFinally2 : " + doTryFinally2());
        System.out.println("doTryFinally3 : " + doTryFinally3());
        System.out.println("doTryFinally4 : " + doTryFinally4());
        System.out.println("doTryFinally5 : " + doTryFinally5());
        System.out.println("doTryFinally6 : " + doTryFinally6());
    }

    public static String doTryFinally1() {
        String name = null;
        try {
            name = "zhuang";
            return name;
        } finally {
            name = "zhuang111111";
        }
    }

    public static String doTryFinally2() {
        String name = null;
        try {
            name = "zhuang";
            return name;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            name = "zhuang111111";
            return name;
        }
    }

    public static String doTryFinally3() {
        String name = null;
        try {
            name = "zhuang";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            name = "zhuang111111";
            return name;
        }
    }

    public static int doTryFinally4() {
        int a = 0;
        try {
            a = 10;
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a++;
            return a;
        }
    }

    public static String doTryFinally5() {
        String name = null;
        try {
            name = "zhuang";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            name = "zhuang111111";
        }
        return name;
    }

    public static String doTryFinally6() {
        String name = null;
        try {
            name = "zhuang";
            int a = 1/0;
            throw new MyException("a");
        } catch (MyException e2) {
            name = "555555";
            return name;
        } catch (Exception e) {
            name = "444444";
            return name;
        } finally {
            name = "zhuang111111";
        }
//        return name;
    }

    static class MyException extends Exception {
        public MyException(String msg) {
            super(msg);
        }
    }
}
