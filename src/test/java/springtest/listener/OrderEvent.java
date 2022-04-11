package springtest.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * @author Vin lan
 * @className OrderEvent
 * @description
 * @createTime 2022-04-11  11:58
 **/
public class OrderEvent extends ApplicationEvent {
    /** 订单编号*/
    private String orderCode;
    /** 商品编号*/
    private String goodsCode;
    /** 红包编号*/
    private String redPacketCode;

    /** 事件的构造函数*/
    public OrderEvent(ApplicationContext source, String orderCode, String goodsCode, String redPacketCode) {
        super(source);
        this.orderCode = orderCode;
        this.goodsCode = goodsCode;
        this.redPacketCode = redPacketCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getRedPacketCode() {
        return redPacketCode;
    }

    public void setRedPacketCode(String redPacketCode) {
        this.redPacketCode = redPacketCode;
    }
}
