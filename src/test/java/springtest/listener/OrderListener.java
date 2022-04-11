package springtest.listener;

import org.springframework.context.ApplicationListener;

/**
 * @author Vin lan
 * @className OrderListener
 * @description
 * @createTime 2022-04-11  12:01
 **/
public class OrderListener implements ApplicationListener<OrderEvent> {
    @Override
    public void onApplicationEvent(OrderEvent event) {
        System.out.println("订单监听器监听到下单事件,订单号为:" + event.getOrderCode());
        // todo 处理订单
    }
}
