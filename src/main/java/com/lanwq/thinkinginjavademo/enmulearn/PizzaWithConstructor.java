package com.lanwq.thinkinginjavademo.enmulearn;

/**
 * @author Vin lan
 * @className Pizza
 * @description 自定义枚举方法
 * @createTime 2021-03-16  15:55
 **/
public class PizzaWithConstructor {
    private PizzaStatus status;

    public enum PizzaStatus {
        ORDERED(5) {
            @Override
            public boolean isOrdered() {
                return true;
            }
        },
        READY(2) {
            @Override
            public boolean isReady() {
                return true;
            }
        },
        DELIVERED(0) {
            @Override
            public boolean isDelivered() {
                return true;
            }
        };

        public boolean isOrdered() {
            return false;
        }

        public boolean isReady() {
            return false;
        }

        public boolean isDelivered() {
            return false;
        }

        private int timeToDelivery;

        public int getTimeToDelivery() {
            return timeToDelivery;
        }

        PizzaStatus(int timeToDelivery) {
            this.timeToDelivery = timeToDelivery;
        }
    }

    public PizzaStatus getStatus() {
        return status;
    }

    public void setStatus(PizzaStatus status) {
        this.status = status;
    }

    public boolean isDeliverable() {
        return this.status.isReady();
    }

    public void printTimeToDeliver() {
        System.out.println("Time to delivery is " +
                this.getStatus().getTimeToDelivery());
    }

    public static void main(String[] args) {
        PizzaWithConstructor testPz = new PizzaWithConstructor();
        testPz.setStatus(PizzaWithConstructor.PizzaStatus.READY);
        System.out.println(testPz.isDeliverable());
    }
}
