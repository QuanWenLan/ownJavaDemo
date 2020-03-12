package com.lanwq.thinking_in_java_demo.demo.sevenreusingclass;

/**
 * @program: ThinkingInJavaDemo -->FinalOverridingIllusion
 * @Description : <blue>final 和 private关键字修饰方法</blue>
 * @author: lanwenquan
 * @creatTime: 2019-11-27 22 : 23
 **/

class WithFinals {
    private final void f() {
        System.out.println("withfinals.f()");
    }

    private void g() {
        System.out.println("withfinals.g()");
    }
}

class OverridingPrivate extends WithFinals {
    private final void f() {
        System.out.println("overridingprivate.f()");
    }

    private void g() {
        System.out.println("overridingprivate.g()");
    }
}

class OverridingPrivate2 extends OverridingPrivate {
    private final void f() {
        System.out.println("overridingprivate2.f()");
    }

    private void g() {
        System.out.println("overridingprivate2.g()");
    }
}

public class FinalOverridingIllusion {
    public static void main(String[] args) {
        OverridingPrivate2 op2 = new OverridingPrivate2();
//        op2.f();  不能访问
        OverridingPrivate op = op2;  // 向上转型
//        op.f(); // 不能访问
        WithFinals withFinals = op;  // 向上转型

    }
}
