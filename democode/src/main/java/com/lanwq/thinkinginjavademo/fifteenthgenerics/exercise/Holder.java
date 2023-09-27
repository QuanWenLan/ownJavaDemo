package com.lanwq.thinkinginjavademo.fifteenthgenerics.exercise;


/**
 * @author Vin lan
 * @className Holder
 * @description TODO
 * @createTime 2020-11-05  17:37
 **/
public class Holder<A, B, C> {
    private A a;
    private B b;
    private C c;

    public Holder(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static void main(String[] args) {
        Holder<String, Integer, Float> holder = new Holder<>("ONE", 1, 10.2f);
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }
}
