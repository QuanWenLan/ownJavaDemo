package springtest.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @author Vin lan
 * @className TestEvent
 * @description 定义监听事件
 * @createTime 2021-08-26  11:25
 **/
public class TestEvent extends ApplicationEvent {
    public String msg;
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public TestEvent(Object source) {
        super(source);
    }

    public TestEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public void print() {
        System.out.println(msg);
    }
}
