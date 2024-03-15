package proxy.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import proxy.jdkproxy.Apple;

import java.lang.reflect.Method;

/**
 * @ClassName CGlibAgent
 * @Description TODO   如果目标对象的实现类实现了接口，Spring AOP 将会采用 JDK 动态代理来生成 AOP 代理类；
 * TODO 如果目标对象的实现类没有实现接口，Spring AOP 将会采用 CGLIB 来生成 AOP 代理类
 * @Author lanwenquan
 * @Date 2020/4/9 15:47
 */
public class CGlibAgent implements MethodInterceptor {
    private Object proxy;

    public Object getInstance(Object proxy) {
        this.proxy = proxy;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.proxy.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    /**
     *
     * @param o 代理的对象（增强对象）
     * @param method 被拦截的方法（需要增强的方法）
     * @param objects 方法入参
     * @param methodProxy 用于调用原始方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(">>>>before invoking");
        //真正调用
        Object ret = methodProxy.invokeSuper(o, objects);
        System.out.println(">>>>after invoking");
        return ret;
    }

    public static void main(String[] args) {
        CGlibAgent cGlibAgent = new CGlibAgent();   // 可以对类进行代理
        Apple apple = (Apple) cGlibAgent.getInstance(new Apple());
        System.out.println(apple.show());
    }
}
