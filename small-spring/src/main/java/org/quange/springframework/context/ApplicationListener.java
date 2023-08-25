package org.quange.springframework.context;

import java.util.EventListener;

/**
 * @author Lan
 * @createTime 2023-08-25  11:58
 **/
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);

}
