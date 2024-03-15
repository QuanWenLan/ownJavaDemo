package proxy.jdkproxy;

import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName ReflectTest
 * @Description TODO
 * @Author lanwenquan
 * @Date 2020/4/9 13:21
 */
public class ReflectTest {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        // 这个会生成一个代理类在我们的文件目录下面，因为我们配置了生成文件。 package com.sun.proxy;  $Proxy0
        //注意一定要返回接口，不能返回实现类否则会报错
        Fruit fruit = (Fruit) DynamicAgent.getAgent(Fruit.class, new Apple());
//        System.out.println(fruit);
        fruit.show();
        fruit.sale();
        // 放开 System.out.println(fruit) 的输出
        /**
         * >>>>>before invoking method
         * method.getName() = toString
         * proxy.jdkproxy.Apple@6979e8cb
         * >>>>>after invoking method
         * proxy.jdkproxy.Apple@6979e8cb
         * >>>>>before invoking method
         * method.getName() = show
         * apple show, i am a apple <<< invoked
         * 1
         * >>>>>after invoking method
         * >>>>>before invoking method
         * method.getName() = sale
         * 卖水果了。。。
         * 0
         * >>>>>after invoking method
         * Disconnected from the target VM, address: '127.0.0.1:59280', transport: 'socket'
         *
         * Process finished with exit code 0
         */
        // 注释掉的输出
        /**
         * >>>>>before invoking method
         * method.getName() = show
         * apple show, i am a apple <<< invoked
         * 1
         * >>>>>after invoking method
         * >>>>>before invoking method
         * method.getName() = sale
         * 卖水果了。。。
         * 0
         * >>>>>after invoking method
         */
    }
}
