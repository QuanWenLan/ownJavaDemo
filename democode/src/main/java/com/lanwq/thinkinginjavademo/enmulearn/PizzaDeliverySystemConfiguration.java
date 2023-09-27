package com.lanwq.thinkinginjavademo.enmulearn;

/**
 * @author Vin lan
 * @className PizzaDeliverySystemConfiguration
 * @description  实现单例模式
 * @createTime 2021-03-16  16:47
 **/
public enum PizzaDeliverySystemConfiguration {
    INSTANCE;

    PizzaDeliverySystemConfiguration() {
        // Initialization configuration which involves
        // overriding defaults like delivery strategy
    }

    private PizzaDeliveryStrategy deliveryStrategy = PizzaDeliveryStrategy.NORMAL;

    public static PizzaDeliverySystemConfiguration getInstance() {
        return INSTANCE;
    }

    public PizzaDeliveryStrategy getDeliveryStrategy() {
        return deliveryStrategy;
    }

    public static void main(String[] args) {
        PizzaDeliverySystemConfiguration instance = PizzaDeliverySystemConfiguration.getInstance();
        PizzaDeliveryStrategy deliveryStrategy = PizzaDeliverySystemConfiguration.getInstance().getDeliveryStrategy();

    }
}
