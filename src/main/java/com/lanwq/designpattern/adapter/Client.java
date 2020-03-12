package com.lanwq.designpattern.adapter;

/**
 * @ClassName Client
 * @Description TODO 想使用特殊的请求 客户端代码
 * @Author lanwenquan
 * @Date 2020/1/10 17:06
 */
public class Client {
    public static void main(String[] args) {
        Target original = new Original();
        original.request();

        // 使用新的接口
        Target adapter = new Adapter();
        adapter.request();
    }
}