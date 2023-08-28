package org.quange.springframework.aop.framework;

/**
 * @author Lan
 * @createTime 2023-08-28  10:16
 **/
public class DefaultAopProxyFactory implements AopProxyFactory {
    @Override
    public AopProxy createAopProxy(AdvisedSupport config) {
        if (config.isProxyTargetClass()) {
            return new Cglib2AopProxy(config);
        }

        return new JdkDynamicAopProxy(config);
    }
}
