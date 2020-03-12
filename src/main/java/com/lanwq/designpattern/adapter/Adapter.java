package com.lanwq.designpattern.adapter;

/**
 * @ClassName Adapter
 * @Description TODO 将客户想使用的接口适配
 * @Author lanwenquan
 * @Date 2020/1/10 17:09
 */
public class Adapter implements Target{

    // 这个是需要适配的接口或者类，Adaptee是一个接口
    private Adaptee adaptee = new AdapteeIm();
    @Override
    public void request() {
        adaptee.specialRequest();
    }
}