package com.lanwq.thread.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author Vin lan
 * @className LongEventFactory
 * @description TODO 让 disruptor 来为我们创建事件，申明一个 EventFactory 来创建实例化 Event 对象。
 * @createTime 2021-01-04  15:43
 **/
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
