package com.lanwq.thinkinginjavademo.classrtti.proxy;

/**
 * @author Vin lan
 * @className SimpleProxyDemo
 * @description TODO 《Java编程思想》描述
 * 代理是基本的设计模式之一，它是<p>你为了提供额外的或不同的操作，而插入的用来代替“实际”对象的对象</p>
 * ===>
 * <p>也就是说我要实现一些额外的操作而不动“真实的对象”的时候，通过写一个代理类来代理“实际的对象”</p>
 * @createTime 2020-10-13  16:52
 **/

/**
 * 简单静态代理
 */
interface Interface {
    void doSomething();

    void somethingElse(String arg);
}

class RealObject implements Interface {

    public void doSomething() {
        System.out.println("doSomething");
    }

    public void somethingElse(String arg) {
        System.out.println("somethingElse" + arg);
    }
}

//简单的代理的示例
class SimpleProxy implements Interface {
    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    public void doSomething() {
        System.out.println("SimpleProxy doSomething");
        proxied.doSomething();
    }

    public void somethingElse(String arg) {
        System.out.println("SimpleProxy somethingElse" + arg);
        proxied.somethingElse(arg);
    }
}


public class SimpleProxyDemo {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse(" lwq");
    }

    public static void main(String[] args) {
        //不使用代理
        consumer(new RealObject());
        //使用代理
        consumer(new SimpleProxy(new RealObject()));
    }
}
/** 解析：
 * 由于consumer()接收的是Interface，所以我们无法知道正在获得的到底是RealObject还是SimpleProxy，因为两者都实现了Interface。
 * 但是SimpleProxy已经插入到了客户端和RealObject之间，因此他会执行操作，然后调用RealObject上相同的方法。
 */
