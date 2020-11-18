package com.lanwq.thinkinginjavademo.fifteenthgenerics.exercise;

import net.mindview.util.FiveTuple;

/**
 * @author Vin lan
 * @className SixTuple
 * @description TODO
 * @createTime 2020-11-06  16:37
 **/
public class SixTuple<A, B, C, D, E, F> extends FiveTuple<A, B ,C, D, E> {
    public final F sixth;
    public SixTuple(A a, B b, C c, D d, E e, F f) {
        super(a, b, c, d, e);
        this.sixth = f;
    }

    public String toString() {
        return "(" + this.first + ", " + this.second + ", " + this.third + ", " + this.fourth + ", " + this.fifth +
                "," + sixth + ")";
    }

    static SixTuple<String, Integer, Long, Double, Float, Boolean> a() {
        return new SixTuple<String, Integer, Long, Double, Float, Boolean>("hi", 1, 2L,1.3, 1.2f, false);
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
