package org.quange.springframework;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;
import org.quange.springframework.aop.MethodMatcher;
import org.quange.springframework.aop.TargetSource;
import org.quange.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.quange.springframework.aop.framework.AdvisedSupport;
import org.quange.springframework.aop.framework.Cglib2AopProxy;
import org.quange.springframework.aop.framework.JdkDynamicAopProxy;
import org.quange.springframework.aop.framework.ReflectiveMethodInvocation;
import org.quange.springframework.aoptest.IUserServiceAop;
import org.quange.springframework.aoptest.UserServiceAop;
import org.quange.springframework.aoptest.UserServiceAopInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Lan
 * @createTime 2023-08-25  16:24
 **/
public class ApiTestAop {
    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* org.quange.springframework.aoptest.UserServiceAop.*(..))");

        Class<UserServiceAop> clazz = UserServiceAop.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    @Test
    public void test_dynamic() {
        // 目标对象
        IUserServiceAop UserServiceAop = new UserServiceAop();
        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(UserServiceAop));
        advisedSupport.setMethodInterceptor(new UserServiceAopInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* org.quange.springframework.aoptest.IUserServiceAop.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IUserServiceAop proxy_jdk = (IUserServiceAop) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.queryUserInfo());

        // 代理对象(Cglib2AopProxy)
        IUserServiceAop proxy_cglib = (IUserServiceAop) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.register("花花"));
    }

    @Test
    public void test_proxy_class() {
        IUserServiceAop UserServiceAop = (IUserServiceAop) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserServiceAop.class}, (proxy, method, args) -> "你被代理了！");
        String result = UserServiceAop.queryUserInfo();
        System.out.println("测试结果：" + result);

    }

    @Test
    public void test_proxy_method() {
        // 目标对象(可以替换成任何的目标对象)
        Object targetObj = new UserServiceAop();

        // AOP 代理
        IUserServiceAop proxy = (IUserServiceAop) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            // 方法匹配器
            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.quange.springframework.aoptest.IUserServiceAop.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObj.getClass())) {
                    // 方法拦截器
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称：" + invocation.getMethod().getName());
                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };
                    // 反射调用
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                }
                return method.invoke(targetObj, args);
            }
        });

        String result = proxy.queryUserInfo();
        System.out.println("测试结果：" + result);

    }
}
