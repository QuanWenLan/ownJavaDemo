package org.quange.springframework.context.event;

import org.quange.springframework.context.ApplicationContext;
import org.quange.springframework.context.ApplicationEvent;

/**
 * @author Lan
 * @createTime 2023-08-25  11:51
 * Base class for events raised for an {@code ApplicationContext}.
 **/
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * Get the {@code ApplicationContext} that the event was raised for.
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
