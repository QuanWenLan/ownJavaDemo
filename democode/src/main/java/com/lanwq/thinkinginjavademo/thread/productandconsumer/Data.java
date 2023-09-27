package com.lanwq.thinkinginjavademo.thread.productandconsumer;

/**
 * @ClassName Data
 * @Description
 * @Author lanwenquan
 * @Date 2020/06/12 13:55
 */
public class Data {
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Data(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Data{" +
                "number=" + number +
                '}';
    }
}
