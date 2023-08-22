package org.quange.springframework.beans.factory.config;

import org.quange.springframework.beans.factory.BeanFactory;

/**
 * @author Lan
 * @createTime 2023-08-22  14:31
 * Extension of the {@link BeanFactory} interface to be implemented by bean factories that are capable of
 * autowiring, provided that they want to expose this functionality for existing bean instances.
 **/
public interface AutowireCapableBeanFactory extends BeanFactory {
}
