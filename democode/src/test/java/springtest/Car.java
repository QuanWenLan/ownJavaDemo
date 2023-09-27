package springtest;

/**
 * @author Vin lan
 * @className Car
 * @description 测试实现 FactoryBean 接口
 * @createTime 2021-08-19  16:13
 **/
public class Car {
    private int maxSpeed;
    private String brand;
    private double price;

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
