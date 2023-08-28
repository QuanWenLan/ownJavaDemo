package org.quange.springframework.aop;

import com.sun.istack.internal.Nullable;

import java.lang.reflect.Method;

/**
 * @author Lan
 * @createTime 2023-08-28  10:03
 **/
public interface MethodBeforeAdvice extends BeforeAdvice {

    /**
     * Callback before a given method is invoked.
     * @param method the method being invoked
     * @param args the arguments to the method
     * @param target the target of the method invocation. May be {@code null}.
     * @throws Throwable if this object wishes to abort the call.
     * Any exception thrown will be returned to the caller if it's
     * allowed by the method signature. Otherwise the exception
     * will be wrapped as a runtime exception.
     */
    void before(Method method, Object[] args, @Nullable Object target) throws Throwable;

}
