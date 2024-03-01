package com.lanwq.thread.bingfazhimei.threadCommunication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vin lan
 * @className UseVolatile
 * @description 使用 volatile 关键字 来实现通信
 * @createTime 2022-06-23  11:58
 **/
public class UseVolatile {
    /**
     *     定义共享变量来实现通信，他需要 volatile 来修饰，肉则县城不能及时感知，可见性
      */
    static volatile boolean notice = false;

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        //线程A
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                list.add("abc");
                System.out.println("线程A添加元素，此时list的size为：" + list.size());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (list.size() == 5) {
                    notice = true;
                    System.out.println("threadA list = " + list);
                }
            }
        });

        //线程A
        Thread threadB = new Thread(() -> {
           while (true) {
               if (notice) {
                   System.out.println("线程B收到通知，开始执行自己的业务...");
                   list.add("bbc");
                   System.out.println("threadB list = " + list);
                   break;
               }
           }
        });
        //需要先启动线程B
        threadB.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 再启动线程A
        threadA.start();
    }
}
