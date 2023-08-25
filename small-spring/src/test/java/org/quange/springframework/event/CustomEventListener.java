package org.quange.springframework.event;

import org.quange.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @author Lan
 * @createTime 2023-08-25  14:11
 **/
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
