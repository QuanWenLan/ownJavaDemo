package com.lanwq.thinkinginjavademo.fourteenthclassrtti.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Vin lan
 * @className SimpleDynamicProxy
 * @description TODO Java动态代理
 * @createTime 2020-10-13  17:01
 **/
class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("***** proxy:" + proxy.getClass() + ",***** method:" + method + ",***** args:" + args);
        if (args != null) {
            for (Object arg : args) {
                System.out.println("arg:" + arg);
            }
        }
        return method.invoke(proxied, args);
    }
}


public class SimpleDynamicProxy {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse(" lwq");
    }

    public static void main(String[] args) {
        RealObject realObject = new RealObject();
        consumer(realObject);
        // insert a proxy and call again
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(), new Class[]{Interface.class}, new DynamicProxyHandler(realObject));
        consumer(proxy);
    }
}
/**
 * 我们可以通过调用静态方法Proxy.newProxyInstance()可以创建动态代理，这个方法需要得到一个类加载器：Interface.class.getClassLoader()，
 * 一个你希望该代理实现的接口列表new Class[]{Interface.class}，以及InvocationHandler接口的一个实现类new DynamicProxyHandler(real)。
 * 动态代理可以将所有调用重定向到调用处理器，因此我们通常会向调用处理器的构造器传递一个“实际对象”的引用，从而使得调用处理器在执行其中介任务时，可以将请求转发。
 * invoke()方法中传递进来了代理对象，在invoke()内部，在代理上调用方法时需要格外小心，因为对接口的调用将被重定向为对代理的调用
 */
