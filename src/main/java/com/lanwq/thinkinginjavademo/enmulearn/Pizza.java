package com.lanwq.thinkinginjavademo.enmulearn;

/**
 * @author Vin lan
 * @className Pizza
 * @description 自定义枚举方法
 * @createTime 2021-03-16  15:55
 **/
public class Pizza {
    private PizzaStatus status;

    public enum TestColor {
        GREEN, RED;
    }

    public enum PizzaStatus {
        ORDERED,
        READY,
        DELIVERED;
    }

    // 可以直接使用 == 来比较
    public boolean isDeliverable() {
        return getStatus() == PizzaStatus.READY;
    }

    // 使用switch case
    public int getDeliveryTimeInDays() {
        switch (status) {
            case ORDERED:
                return 5;
            case READY:
                return 2;
            case DELIVERED:
                return 0;
        }
        return 0;
    }

    /**
     * 用来配合{@link PizzaDeliveryStrategy 使用}
      */
    public void deliver() {
        if (isDeliverable()) {
            PizzaDeliverySystemConfiguration.getInstance().getDeliveryStrategy()
                    .deliver(this);
            this.setStatus(PizzaStatus.DELIVERED);
        }
    }

    public PizzaStatus getStatus() {
        return status;
    }

    public void setStatus(PizzaStatus status) {
        this.status = status;
    }

    public static void main(String[] args) {
        Pizza.PizzaStatus pizzaStatus = null;
//        System.out.println(pizzaStatus.equals(PizzaStatus.DELIVERED)); // 空指针异常
        System.out.println(pizzaStatus == PizzaStatus.DELIVERED); // 正常运行
        // 编译时安全性
        if (PizzaStatus.DELIVERED.equals(TestColor.GREEN)) { // 编译通过

        }
        /*if(PizzaStatus.DELIVERED == TestColor.GREEN) {  // 编译不通过

        }*/

        // test switch case
        Pizza pizza = new Pizza();
        pizza.setStatus(PizzaStatus.ORDERED);
        System.out.println(pizza.getDeliveryTimeInDays());
    }
}
/**
 * 因为枚举类型确保JVM中仅存在一个常量实例，所以我们可以安全的时候 == 来比较两个变量；此外， ==
 * 还提供编译时和运行时的安全性。
 */
