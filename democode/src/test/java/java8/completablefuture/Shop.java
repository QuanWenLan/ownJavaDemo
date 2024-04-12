package java8.completablefuture;

/**
 * @author Lan
 * @createTime 2024-04-08  15:39
 **/
public class Shop {
    private String price;
    private String name;

    public Shop(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice(String product) {
        return price;
    }
}
