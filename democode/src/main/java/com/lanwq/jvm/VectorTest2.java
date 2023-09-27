package com.lanwq.jvm;

import java.util.Vector;

/**
 * @program: javaDemo->VectorTest
 * @description: 测试不是绝对线程安全
 * @author: lanwenquan
 * @date: 2021-05-07 22:43
 */
public class VectorTest2 {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (vector) {
                        for (int i = 0; i < vector.size(); i++) {
                            //当前线程让出CPU,使例子中的错误更快出现
                            Thread.yield();
                            vector.remove(i);
                        }
                    }
                }
            });

            Thread printThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (vector) {
                        for (int i = 0; i < vector.size(); i++) {
                            //当前线程让出CPU,使例子中的错误更快出现
                            Thread.yield();
                            System.out.println(vector.get(i));
                        }
                    }
                }
            });

            removeThread.start();

            printThread.start();

            // 不同时产生过多的数据，否则会导致操作系统假死
            while (Thread.activeCount() > 20) ;

        }
    }
}
