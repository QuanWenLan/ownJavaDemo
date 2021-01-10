package com.lanwq.thread.disruptor;

/**
 * @author Vin lan
 * @className LongEvent
 * @description 在 Disruptor 的语义中，生产者和消费者之间进行交换的数据被称为事件(Event)。
 * 它不是一个被 Disruptor 定义的特定类型，而是由 Disruptor 的使用者定义并指定。
 * @createTime 2021-01-04  15:43
 **/
//定义事件event  通过Disruptor 进行交换的数据类型。
public class LongEvent {

    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

}
