package com.lanwq.thread.bingfazhimei;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Lan
 * @createTime 2023-06-30  15:43
 * 弱一致性指的是在一段时间内，不同线程对容器的访问可能看到不同的数据。具体来说，当一个线程对 CopyOnWriteArrayList 执行写操作时，
 * 它会将容器的数据复制一份，然后进行修改，完成后再将修改后的数据替换原来的数据。在这个过程中，读操作可能仍然在读取原来的数据，因此在写操作完成之前，读操作可能无法看到最新的修改。
 *
 * 这种弱一致性的特性使得 CopyOnWriteArrayList 在读多写少的场景下非常适用，因为写操作不会影响读操作的性能，读操作也不会受到写操作的影响。
 * 然而，需要注意的是，在使用 CopyOnWriteArrayList 时，如果对数据的实时性要求较高，可能需要额外的同步机制来确保数据的一致性。
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
