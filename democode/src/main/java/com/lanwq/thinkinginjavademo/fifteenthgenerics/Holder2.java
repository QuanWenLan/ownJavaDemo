package com.lanwq.thinkinginjavademo.fifteenthgenerics;

/**
 * @author Vin lan
 * @className Holder1
 * @description TODO 简单泛型，可以持有Object类型对象,
 * @createTime 2020-11-05  17:17
 **/
public class Holder2 {
    private Object a;

    public Holder2(Object a) {
        this.a = a;
    }

    public Object getA() {
        return a;
    }

    public void setA(Object a) {
        this.a = a;
    }

    // 存储了三种不类型的对象
    public static void main(String[] args) {
        Holder2 holder2 = new Holder2(new Automobile());
        Automobile a = (Automobile) holder2.getA();
        holder2.setA("Not an Automobile");
        String a1 = (String) holder2.getA();
        holder2.setA(1);
        Integer a2 = (Integer) holder2.getA();
    }

    /**
     * 我们可以暂时不指定类型，而是稍后决定使用什么类型。参考:{@link Holder3}
     */
}
