package org.quange.springframework.beans.factory;

/**
 * @author Lan
 * @createTime 2023-08-22  14:17
 *
 * Sub-interface implemented by bean factories that can be part of a hierarchy。
 * <p>The corresponding {@code setParentBeanFactory} method for bean
 * factories that allow setting the parent in a configurable
 * fashion can be found in the ConfigurableBeanFactory interface.
 * 由bean工厂实现的子接口，可以是层次结构的一部分
 **/
public interface HierarchicalBeanFactory extends BeanFactory {
}
