package org.quange.springframework.aop.framework;

/**
 * @author Lan
 * @createTime 2023-08-28  10:49
 **/
public class ProxyFactory {

    private AdvisedSupport advisedSupport;
    private AopProxyFactory aopProxyFactory;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
        this.aopProxyFactory = new DefaultAopProxyFactory();
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        return getAopProxyFactory().createAopProxy(advisedSupport);
    }


    public AopProxyFactory getAopProxyFactory() {
        return this.aopProxyFactory;
    }
}
