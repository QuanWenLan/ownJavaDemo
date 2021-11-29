//: typeinfo/packageaccess/HiddenC.java
package com.lanwq.thinkinginjavademo.fourteenthclassrtti.packageaccess;

import com.lanwq.thinkinginjavademo.fourteenthclassrtti.interfaceandtype.A;

import static net.mindview.util.Print.print;

class C implements A {
  public void f() { print("public C.f()"); }
  public void g() { print("public C.g()"); }
  void u() { print("package C.u()"); }
  protected void v() { print("protected C.v()"); }
  private void w() { print("private C.w()"); }
}
/**
* 在这个包中唯一的public是 HiddenC ，在被调用是将产生A接口类型对象。这里即时返回的是C类型，你在包的外部仍旧不能使用A之外的
* 任何方法（也就是C的方法），因为你不能在包的外部命名 C 。如果在外部转型为C，则被禁止，因为在包的外部没有很合C类型可用。
* {@link com.lanwq.thinkinginjavademo.fourteenthclassrtti.HiddenImplementation }
* */
public class HiddenC {
  public static A makeA() { return new C(); }
} ///:~
