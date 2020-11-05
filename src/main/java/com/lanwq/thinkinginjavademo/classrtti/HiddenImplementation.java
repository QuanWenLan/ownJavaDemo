package com.lanwq.thinkinginjavademo.classrtti;//: typeinfo/HiddenImplementation.java
// Sneaking around package access.


import com.lanwq.thinkinginjavademo.classrtti.interfaceandtype.A;
import com.lanwq.thinkinginjavademo.classrtti.packageaccess.HiddenC;

import java.lang.reflect.Method;

public class HiddenImplementation {
    public static void main(String[] args) throws Exception {
        A a = HiddenC.makeA();
        a.f();
        System.out.println(a.getClass().getName());
        // Compile error: cannot find symbol 'C':
    /* if(a instanceof C) {
      C c = (C)a;
      c.g();
    } */
        // Oops! Reflection still allows us to call g():
        callHiddenMethod(a, "g");
        // And even methods that are less accessible!
        callHiddenMethod(a, "u");
        callHiddenMethod(a, "v");
        callHiddenMethod(a, "w");
    }

    /**
     * 如果知道方法名可以访问所有的方法，可以在Method对象上调用setAccessible(true)
     * 反射天下无敌！！！！！ 使用私有内部类测试 {@link InnerImplementation},
     * 使用匿名内部类测试：{@link AnonymousImplementation}
     * @param a
     * @param methodName
     * @throws Exception
     */
    static void callHiddenMethod(Object a, String methodName)
            throws Exception {
        Method g = a.getClass().getDeclaredMethod(methodName);
        g.setAccessible(true);
        g.invoke(a);
    }
} /* Output:
public C.f()
typeinfo.packageaccess.C
public C.g()
package C.u()
protected C.v()
private C.w()
*///:~
