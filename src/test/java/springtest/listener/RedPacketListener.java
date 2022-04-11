package springtest.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

/**
 * @author Vin lan
 * @className OrderListener
 * @description
 * @createTime 2022-04-11  12:01
 **/
public class RedPacketListener implements ApplicationListener<OrderEvent>, PriorityOrdered {
    @Override
    public void onApplicationEvent(OrderEvent event) {
        if(event.getRedPacketCode()!=null) {
            System.out.println("红包监听器监听到下单事件,红包编号为:" + event.getRedPacketCode());
            //TODO 使用红包处理
        } else {
            System.out.println("红包监听器监听到下单事件，订单:"+event.getOrderCode()+"没有使用红包");
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
