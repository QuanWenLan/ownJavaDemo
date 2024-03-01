package com.lanwq.thread.bingfazhimei;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Lan
 * @createTime 2023-06-30  15:43
 **/
public class CopyOnWriteListDemo {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<Object> arrayList = new CopyOnWriteArrayList<>();
        arrayList.add("hello");
        arrayList.add("AAAA");
        arrayList.add("BBBB");
        arrayList.add("CCCC");
        arrayList.add("DDDD");
        Thread threadThree = new Thread(() -> {
            arrayList.set(1, "AAAA0000");
            arrayList.remove(2);
            arrayList.remove(3);
        });
        // 保证在修改线程启动前获取迭代器
        Iterator<Object> iterator = arrayList.iterator();
        threadThree.start();
        threadThree.join();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        // 迭代器弱一致性
        System.out.println("验证弱一致性====");
        CopyOnWriteListDemo demo = new CopyOnWriteListDemo();
        demo.weakConsistence();
    }

    /**
     * 迭代器的弱一致性
     */
    public void weakConsistence() {
        CopyOnWriteArrayList<Integer> cowal = new CopyOnWriteArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            cowal.add(i);
        }
        PutThread p1 = new PutThread(cowal);
        p1.start();
        Iterator<Integer> iterator = cowal.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        iterator = cowal.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }

    static class PutThread extends Thread {
        private CopyOnWriteArrayList<Integer> cowal;

        public PutThread(CopyOnWriteArrayList<Integer> cowal) {
            this.cowal = cowal;
        }

        public void run() {
            try {
                for (int i = 100; i < 110; i++) {
                    cowal.add(i);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    原文链接：https://pdai.tech/md/java/thread/java-thread-x-juc-collection-CopyOnWriteArrayList.html
}
