package com.lanwq.thinkinginjavademo.enmulearn;

/**
 * @author Vin lan
 * @className PizzaStatus
 * @description 以这种方式定义的常量使代码更具可读性，允许进行编译时检查，预先记录可接受值的列表，并避免由于传入无效值而引起的意外行为。
 * https://www.baeldung.com/a-guide-to-java-enums  https://github.com/Snailclimb/JavaGuide/tree/master/docs/java/basis
 * 定义了三种订单的状态，通过这些代码的定义避免了定义常量
 * @createTime 2021-03-16  15:50
 **/
public enum PizzaStatus {
    /**
     * ORDERED
     */
    ORDERED,
    /**
     * READY
     */
    READY,
    /**
     * DELIVERED
     */
    DELIVERED;
    public static void main(String[] args) {
        System.out.println(PizzaStatus.ORDERED.name());
        System.out.println(PizzaStatus.ORDERED);
        System.out.println(PizzaStatus.ORDERED.name().getClass());
        System.out.println(PizzaStatus.ORDERED.getClass());
    }
}
