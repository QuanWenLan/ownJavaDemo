package com.lanwq.thinkinginjavademo.classrtti;//: typeinfo/InterfaceViolation.java
// Sneaking around an interface.
/**
 * interface 关键字：允许程序员隔离构建，进而降低耦合性。但是通过类型信息，这种耦合性还是会传播出去--
 * 接口并非是对解耦的一种无懈可击的保障。实例如下：
 */

import com.lanwq.thinkinginjavademo.classrtti.interfaceandtype.A;

class B implements A {
  public void f() {}
  public void g() {}
}

public class InterfaceViolation {

  public static void main(String[] args) {
    A a = new B();
    a.f();
    // a.g(); // Compile error
    System.out.println(a.getClass().getName());
    /**
     * 可以使用RTTI来判断是哪个类。这个完全合法和可接受的，但是你也许并不想让客户端程序员这么做，因为这给了一个
     机会，使得他们的代码与你的代码耦合成都超过你的期望。也许你认为interface关键字正在保护你，实际上并没有。
     查看类{@link com.lanwq.thinkinginjavademo.classrtti.packageaccess.HiddenC }*/
    if(a instanceof B) {

      B b = (B)a;
      b.g();
    }
  }
} /* Output:
B
*///:~
