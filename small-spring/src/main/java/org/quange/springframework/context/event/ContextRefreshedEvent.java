package org.quange.springframework.context.event;

import org.quange.springframework.context.ApplicationEvent;

/**
 * @author Lan
 * @createTime 2023-08-25  11:54
 * Event raised when an {@code ApplicationContext} gets initialized or refreshed.
 **/
public class ContextRefreshedEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
