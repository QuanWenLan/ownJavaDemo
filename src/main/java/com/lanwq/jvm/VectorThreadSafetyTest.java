package com.lanwq.jvm;

import java.util.Vector;

/**
 * @author Vin lan
 * @className VectorThreadSafetyTest
 * @description
 * @createTime 2021-04-20  09:39
 **/
public class VectorThreadSafetyTest {
    private static Vector<Integer> vector = new Vector();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                //往vector中添加元素
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (vector) {
                        //获取vector的大小
                        for (int i = 0; i < vector.size(); i++) {
                            //当前线程让出CPU,使例子中的错误更快出现
                            Thread.yield();
                            //移除第i个数据
                            vector.remove(i);
                        }
                    }
                }
            });

            Thread printThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    synchronized (vector) {
                        //获取vector的大小
                        for (int i = 0; i < vector.size(); i++) {
                            //当前线程让出CPU,使例子中的错误更快出现
                            Thread.yield();
                            //获取第i个数据并打印
                            System.out.println(vector.get(i));
                        }
                    }
                }

            });

            removeThread.start();
            printThread.start();
            //避免同时产生过多线程
            while (Thread.activeCount() > 20) ;
        }
    }

}
