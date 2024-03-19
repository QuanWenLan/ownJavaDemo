package com.lanwq.esdemo.staticdata;

import com.lanwq.esdemo.bean.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lan
 * @createTime 2024-03-19  21:45
 **/
public class ItemData {
    public static List<Product> products = new ArrayList<>();
    static {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setProductId("P001");
        product1.setName("华为手机");
        product1.setCategory("手机");
        product1.setBrand("华为");
        product1.setPrice(2999.0);
        product1.setImages("http://example.com/image1.jpg");
        product1.setTitle("华为手机最新款");
        product1.setContent("这是一款华为手机的产品介绍。");
        product1.setState("在售");
        products.add(product1);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setProductId("P002");
        product2.setName("苹果手机");
        product2.setCategory("手机");
        product2.setBrand("苹果");
        product2.setPrice(5999.0);
        product2.setImages("http://example.com/image2.jpg");
        product2.setTitle("苹果手机全新发布");
        product2.setContent("这是一款苹果手机的产品介绍。");
        product2.setState("在售");
        products.add(product2);

        Product product3 = new Product();
        product3.setId(3L);
        product3.setProductId("P003");
        product3.setName("联想笔记本");
        product3.setCategory("电脑");
        product3.setBrand("联想");
        product3.setPrice(4999.0);
        product3.setImages("http://example.com/image3.jpg");
        product3.setTitle("联想笔记本新品上市");
        product3.setContent("这是一款联想笔记本的产品介绍。");
        product3.setState("在售");
        products.add(product3);
    }
}
