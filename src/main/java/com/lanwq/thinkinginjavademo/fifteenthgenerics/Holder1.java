package com.lanwq.thinkinginjavademo.fifteenthgenerics;

/**
 * @author Vin lan
 * @className Holder1
 * @description TODO 简单泛型，只持有一个Automobile 这一个类
 * @createTime 2020-11-05  17:17
 * 无法持有其他类型的类，我们可以修改为持有Object 类，而不是Automobile
 **/
public class Holder1 {
    private Automobile a;

    public Holder1(Automobile a) {
        this.a = a;
    }

    public Automobile getA() {
        return a;
    }
}
class Automobile {

}