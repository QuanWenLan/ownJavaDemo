package org.quange.springframework.context.event;

import org.quange.springframework.context.ApplicationEvent;
import org.quange.springframework.context.ApplicationListener;

/**
 * @author Lan
 * @createTime 2023-08-25  11:56
 **/
public interface ApplicationEventMulticaster {
    /**
     * Add a listener to be notified of all events.
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners.
     * @param event the event to multicast
     */
    void multicastEvent(ApplicationEvent event);
}
