package org.quange.springframework.beans.factory;

/**
 * @author Lan
 * @createTime 2023-08-24  11:32
 **/
public interface BeanClassLoaderAware extends Aware {

    /**
     * Callback that supplies the bean {@link ClassLoader class loader} to
     * a bean instance.
     * <p>Invoked <i>after</i> the population of normal bean properties but
     * <i>before</i> an initialization callback such as
     * {@link InitializingBean InitializingBean's}
     * {@link InitializingBean#afterPropertiesSet()}
     * method or a custom init-method.
     * @param classLoader the owning class loader
     */
    void setBeanClassLoader(ClassLoader classLoader);
}
