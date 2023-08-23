package org.quange.springframework.context;

import org.quange.springframework.beans.factory.HierarchicalBeanFactory;
import org.quange.springframework.beans.factory.ListableBeanFactory;

/**
 * @author Lan
 * @createTime 2023-08-23  09:24
 * Central interface to provide configuration for an application.
 * This is read-only while the application is running, but may be
 * reloaded if the implementation supports this.
 * 为应用程序提供配置的中央接口。在应用程序运行时，这是只读的，但如果实现支持此功能，则可以重新加载。
 * <p>An ApplicationContext provides:
 * <ul>
 * <li>Bean factory methods for accessing application components.
 * Inherited from {@link org.quange.springframework.beans.factory.ListableBeanFactory}.
 * <li>The ability to load file resources in a generic fashion.
 * Inherited from the {@link org.quange.springframework.core.io.ResourceLoader} interface.
 * <li>The ability to publish events to registered listeners.
 * Inherited from the {link ApplicationEventPublisher} interface.
 * <li>The ability to resolve messages, supporting internationalization.
 * Inherited from the {link MessageSource} interface.
 * <li>Inheritance from a parent context. Definitions in a descendant context
 * will always take priority. This means, for example, that a single parent
 * context can be used by an entire web application, while each servlet has
 * its own child context that is independent of that of any other servlet.
 * </ul>
 **/
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory {
}
