package com.lanwq.designpattern.adapter;

/**
 * @ClassName AdapteeIm
 * @Description TODO 想使用接口的实现类
 * @Author lanwenquan
 * @Date 2020/1/10 17:13
 */
public class AdapteeIm implements Adaptee{
    @Override
    public void specialRequest() {
        System.out.println("特殊的请求");
    }
}