package com.lanwq.thread.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author Vin lan
 * @className LongEventHandler
 * @description TODO 事件处理者（消费者，来消费事件）
 * @createTime 2021-01-04  15:45
 **/
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("消费者:" + longEvent.getValue());
    }
}
