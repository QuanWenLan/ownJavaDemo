package com.lanwq.thinkinginjavademo.fourteenthclassrtti;

/**
 * @author Vin lan
 * @className ClassCasts
 * @description TODO 用于Class引用的转型语法，即cast()方法。
 * @createTime 2020-09-18  15:57
 **/
public class ClassCasts {
    public static void main(String[] args) {
        Building b = new House();
        Class<House> houseClass = House.class;
        // cast(obj); 将其转型为Class引用的类型
        House h = houseClass.cast(b);
        h = (House)b; // ...or just do this
    }
}
class Building{}
class House extends Building{}
