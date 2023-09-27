package com.lanwq.thinkinginjavademo.fifteenthgenerics;

import net.mindview.util.FiveTuple;
import net.mindview.util.FourTuple;
import net.mindview.util.ThreeTuple;

/**
 * @author Vin lan
 * @className TupleTest
 * @description TODO 元组测试
 * @createTime 2020-11-06  16:19
 **/
class Amphibian {
}

class Vehicle {
}

public class TupleTest {
    static TwoTuple<String, Integer> f() {
        return new TwoTuple<String, Integer>("hi", 47);
    }

    static ThreeTuple<Amphibian, String, Integer> g() {
        return new ThreeTuple<>(new Amphibian(), "hi", 47);
    }

    static FourTuple<Vehicle, Amphibian, String, Integer> h() {
        return new FourTuple<>(new Vehicle(), new Amphibian(), "hi", 47);
    }

    static FiveTuple<Vehicle, Amphibian, String, Integer, Double> k() {
        return new FiveTuple<>(new Vehicle(), new Amphibian(), "hi", 47, 11.1);
    }

    public static void main(String[] args) {
        TwoTuple<String, Integer> f = f();
//        f.first = "there";// compile error: final
        System.out.println(f);
        System.out.println(g());
        System.out.println(h());
        System.out.println(k());
    }
}
