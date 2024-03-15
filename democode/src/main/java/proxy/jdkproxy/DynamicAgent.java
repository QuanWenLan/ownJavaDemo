package proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName DynamicAgent
 * @Description TODO  JDK动态代理模式只能代理接口而不能代理类
 * @Author lanwenquan
 * @Date 2020/4/9 13:11
 */
public class DynamicAgent {

    // 1 首先要有实现处理代理的类的接口的 实现
    static class MyHandler implements InvocationHandler {
        private Object proxy; // 要代理的类（真实的类）

        public MyHandler(Object proxy) {
            this.proxy = proxy;
        }

        // 对于被代理的类的操作都会由该接口中的invoke方法，原理是通过Proxy.newProxyInstance返回的类已经不是原来的类的，是我们目标类的代理类，
        // 它实现了目标类的接口，当代理类调用接口的时候，如调用show方法，(Integer)super.h.invoke(this, m3, (Object[])null);
        // h 是 InvocationHandler h，也就是我们实现InvocationHandler的MyHandler了

        /**
         * public final class $Proxy0 extends Proxy implements Fruit {
         *     private static Method m1;
         *     private static Method m3;
         *     private static Method m2;
         *     private static Method m4;
         *     private static Method m0;
         *
         *     public $Proxy0(InvocationHandler var1) throws  {
         *         super(var1);
         *     }
         *
         *     public final int show() throws  {
         *         try {
         *             return (Integer)super.h.invoke(this, m3, (Object[])null);
         *         } catch (RuntimeException | Error var2) {
         *             throw var2;
         *         } catch (Throwable var3) {
         *             throw new UndeclaredThrowableException(var3);
         *         }
         *     }
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(">>>>>before invoking method"); // 对其增强操作
            System.out.println("method.getName() = " + method.getName()); // 通过这个可以查看到调用了哪个方法
            Object invoke = method.invoke(this.proxy, args);
            System.out.println(invoke);
            System.out.println(">>>>>after invoking method"); // 对其增强操作
            return invoke;
        }
    }

    // 2 再获取一个代理对象，传入要代理的类，返回一个代理类，代理类实现对真实类的操作接口（第一步）
    public static Object getAgent(Class interfaceClazz, Object proxy) {
//        自动生成 $Proxy0.class 源代码
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        return Proxy.newProxyInstance(interfaceClazz.getClassLoader(), new Class[]{interfaceClazz},
                new MyHandler(proxy));
    }
}
