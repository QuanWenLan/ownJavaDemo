package springtest.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Vin lan
 * @className MyInvocationHandler
 * @description
 * @createTime 2021-08-30  14:58
 **/
public class MyInvocationHandler implements InvocationHandler {
    // 目标对象
    private Object target;

    /**
     *
     * @param target 目标对象
     */
    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-------------------before------------------");
        Object result = method.invoke(target, args);
        System.out.println("-------------------after-------------------");
        return result;
    }

    /**
     * 获取目标对象的代理对象
     * @return 代理对象
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader()
        ,target.getClass().getInterfaces(), this);
    }
}
