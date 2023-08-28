package org.quange.springframework;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;
import org.quange.springframework.aop.ClassFilter;
import org.quange.springframework.aop.MethodMatcher;
import org.quange.springframework.aop.TargetSource;
import org.quange.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.quange.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.quange.springframework.aop.framework.AdvisedSupport;
import org.quange.springframework.aop.framework.ProxyFactory;
import org.quange.springframework.aop.framework.ReflectiveMethodInvocation;
import org.quange.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.quange.springframework.aoptest.IUserServiceAop;
import org.quange.springframework.aoptest.UserServiceAop;
import org.quange.springframework.aoptest.UserServiceAopInterceptor;
import org.quange.springframework.aoptest.UserServiceBeforeAdvice;
import org.quange.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Lan
 * @createTime 2023-08-28  11:11
 **/
public class ApiTestAop2 {

    private AdvisedSupport advisedSupport;

    @Before
    public void init() {
        // 目标对象
        IUserServiceAop userServiceAop = new UserServiceAop();
        // 组装代理信息
        advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userServiceAop));
        advisedSupport.setMethodInterceptor(new UserServiceAopInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* org.quange.springframework.aoptest.IUserServiceAop.*(..))"));
    }

    @Test
    public void test_proxyFactory() {
        advisedSupport.setProxyTargetClass(false); // false/true，JDK动态代理、CGlib动态代理
        IUserServiceAop proxy = (IUserServiceAop) new ProxyFactory(advisedSupport).getProxy();

        System.out.println("测试结果：" + proxy.queryUserInfo());
    }

    @Test
    public void test_beforeAdvice() {
        UserServiceBeforeAdvice beforeAdvice = new UserServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor interceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
        advisedSupport.setMethodInterceptor(interceptor);

        IUserServiceAop proxy = (IUserServiceAop) new ProxyFactory(advisedSupport).getProxy();
        System.out.println("测试结果：" + proxy.queryUserInfo());
    }

    @Test
    public void test_advisor() {
        // 目标对象
        IUserServiceAop UserServiceAop = new UserServiceAop();

        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* org.quange.springframework.aoptest.IUserServiceAop.*(..))");
        advisor.setAdvice(new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvice()));

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(UserServiceAop.getClass())) {
            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = new TargetSource(UserServiceAop);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(true); // false/true，JDK动态代理、CGlib动态代理

            IUserServiceAop proxy = (IUserServiceAop) new ProxyFactory(advisedSupport).getProxy();
            System.out.println("测试结果：" + proxy.queryUserInfo());
        }
    }

    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-aop-test.xml");

        IUserServiceAop UserServiceAop = applicationContext.getBean("userService", IUserServiceAop.class);
        System.out.println("测试结果：" + UserServiceAop.queryUserInfo());
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
