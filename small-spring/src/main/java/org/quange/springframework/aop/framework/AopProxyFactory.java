package org.quange.springframework.aop.framework;

/**
 * @author Lan
 * @createTime 2023-08-28  10:15
 **/
public interface AopProxyFactory {

    /**
     * Create an {@link AopProxy} for the given AOP configuration.
     * @param config the AOP configuration in the form of an
     * AdvisedSupport object
     * @return the corresponding AOP proxy
     */
    AopProxy createAopProxy(AdvisedSupport config);
}
