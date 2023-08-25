package org.quange.springframework.context.event;

import org.quange.springframework.context.ApplicationContext;
import org.quange.springframework.context.ApplicationEvent;

/**
 * @author Lan
 * @createTime 2023-08-25  11:52
 * Event raised when an {@code ApplicationContext} gets closed.
 **/
public class ContextClosedEvent extends ApplicationEvent {
    /**
     * Creates a new ContextClosedEvent.
     *
     * @param source the {@code ApplicationContext} that has been closed
     *               (must not be {@code null})
     */
    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }
}
