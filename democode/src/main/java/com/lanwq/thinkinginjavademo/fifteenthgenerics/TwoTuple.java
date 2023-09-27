//: net/mindview/com.lanwq.other.util/TwoTuple.java
package com.lanwq.thinkinginjavademo.fifteenthgenerics;


public class TwoTuple<A, B> {
  /**
   *  这里定义为了public final而不是private，这样客户端程序可以读取first和second对象，但是却不能
   *  赋值给first或second。====》安全性。可以使用集成来产生跟多的元组。{@link net.mindview.util.ThreeTuple}或者
   *  {@link net.mindview.util.FourTuple}, {@link net.mindview.util.FiveTuple}
    */
    public final A first;
    public final B second;

    public TwoTuple(A a, B b) {
        first = a;
        second = b;
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }
} ///:~
/**
 * 这个概念称为“元组”，它是将一组镀锡量直接打包存储于其中的一个单一对象这个容易对象允许读取其中元素
 * 但是不允许向其中存放新的对象。（这个对象也称为数据传送对象或信使）
 */