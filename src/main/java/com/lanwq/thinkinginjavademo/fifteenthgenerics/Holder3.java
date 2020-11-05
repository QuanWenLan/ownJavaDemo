package com.lanwq.thinkinginjavademo.fifteenthgenerics;

/**
 * @author Vin lan
 * @className Holder3
 * @description TODO
 * @createTime 2020-11-05  17:27
 **/
public class Holder3<T> {
    private T a;

    public Holder3(T a) {
        this.a = a;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public static void main(String[] args) {
        // 创建对象时必须指明该容器想持有什么类型的对象，将其置于尖括号内。（子类或者基类）
        Holder3<Automobile> holder3 = new Holder3<Automobile>(new Automobile());
        Automobile a = holder3.getA(); // No cast need
//        holder3.setA("Not an Automobile"); // Error
//        holder3.setA(1); // error
    }
}
