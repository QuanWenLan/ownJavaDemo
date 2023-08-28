package org.quange.springframework.aoptest;

import org.quange.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author Lan
 * @createTime 2023-08-28  11:02
 **/
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }
}
