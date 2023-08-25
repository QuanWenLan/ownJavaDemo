package org.quange.springframework.context;

import java.util.EventObject;

/**
 * @author Lan
 * @createTime 2023-08-25  11:49
 **/
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
