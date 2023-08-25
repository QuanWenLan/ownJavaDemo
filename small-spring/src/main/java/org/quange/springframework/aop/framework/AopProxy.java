package org.quange.springframework.aop.framework;

/**
 * @author Lan
 * @createTime 2023-08-25  15:52
 **/
public interface AopProxy {

    /**
     * Create a new proxy object.
     * <p>Uses the AopProxy's default class loader (if necessary for proxy creation):
     * usually, the thread context class loader.
     * @return the new proxy object (never {@code null})
     * @see Thread#getContextClassLoader()
     */
    Object getProxy();
}
