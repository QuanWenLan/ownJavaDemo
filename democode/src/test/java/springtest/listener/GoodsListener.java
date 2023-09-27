package springtest.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

/**
 * @author Vin lan
 * @className OrderListener
 * @description
 * @createTime 2022-04-11  12:01
 **/
public class GoodsListener implements ApplicationListener<OrderEvent>, Ordered {
    @Override
    public void onApplicationEvent(OrderEvent event) {
        System.out.println("商品监听器监听到下单事件,更新商品库存:" + event.getOrderCode());
        // todo 更新商品库存
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
