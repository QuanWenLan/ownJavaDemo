package com.lanwq.designpattern.adapter;

/**
 * @ClassName Original
 * @Description TODO 原始客户使用的接口的实现类
 * @Author lanwenquan
 * @Date 2020/1/10 17:14
 */
public class Original implements Target {
    @Override
    public void request() {
        System.out.println("普通请求");
    }
}