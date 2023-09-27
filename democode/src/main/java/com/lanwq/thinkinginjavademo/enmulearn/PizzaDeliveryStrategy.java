package com.lanwq.thinkinginjavademo.enmulearn;

/**
 * @author Vin lan
 * @className PizzaDeliveryStrategy
 * @description 实现策略模式
 * 通常，策略模式由不同类实现同一个接口来实现的。
 * 这也就意味着添加新策略意味着添加新的实现类。使用枚举，可以轻松完成此任务，添加新的实现意味着只定义具有某个实现的另一个实例。
 * @createTime 2021-03-16  16:47
 **/
public enum PizzaDeliveryStrategy {
    EXPRESS {
        @Override
        public void deliver(Pizza pz) {
            System.out.println("Pizza will be delivered in express mode");
        }
    },
    NORMAL {
        @Override
        public void deliver(Pizza pz) {
            System.out.println("Pizza will be delivered in normal mode");
        }
    };

    public abstract void deliver(Pizza pz);  // 定义成了一个abstract方法，给到具体的枚举实例去重写

    public static void main(String[] args) {
        Pizza pz = new Pizza();
        pz.setStatus(Pizza.PizzaStatus.READY);
        pz.deliver();
        System.out.println((pz.getStatus() == Pizza.PizzaStatus.DELIVERED));
    }
}
