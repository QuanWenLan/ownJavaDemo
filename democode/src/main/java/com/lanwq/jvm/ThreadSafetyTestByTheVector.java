package com.lanwq.jvm;

import java.util.Vector;

/**
 * @program: javaDemo->ThreadSafetyTestByTheVector
 * @description: 测试绝对的线程安全
 * @author: lanwenquan
 * @date: 2021-04-19 22:59
 */
public class ThreadSafetyTestByTheVector {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            });


            Thread printThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        System.out.println((vector.get(i)));
                    }
                }
            });

            removeThread.start();
            printThread.start();

            while (Thread.activeCount() > 21) {
                //打印java启动时创建了哪些线程
/*                for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
                    System.out.println("========" + entry.getKey());
                }*/
                //打印当前活跃线程列表
                Thread.currentThread().getThreadGroup().list();
                break;
            }
        }

    }
}
