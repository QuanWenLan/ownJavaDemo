package com.lanwq.java8.trycatch;

/**
 * @author Vin lan
 * @className TryWithResource
 * @description TODO Java8 try catch 不需要使用finally来进行一些通道，接口的关闭
 * @createTime 2020-09-03  11:09
 * 实现这个功能必须要实现AutoClosable接口，该接口要重写一个close方法
 * https://juejin.im/entry/6844903446185951240
 **/
public class TryWithResource {
    /**
     * 使用这种语法糖之后会出现异常屏蔽的问题，参考例子 {@link com.lanwq.java8.trycatch.TryWithResource2},
     * 使用这种语法要注意 资源泄露问题，也就是要关注对应的 close() 方法的具体实现。
     */
    public static void main(String[] args) {
        // 使用这种语法糖之后，会默认调用 close方法
        try (Connection connection = new Connection()) {
            connection.sendData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
