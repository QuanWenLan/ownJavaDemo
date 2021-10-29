package com.lanwq.thread.productandconsumer;

/**
 * @ClassName ProducerConsumer
 * @Description https://blog.csdn.net/shadowcw/article/details/82352829
 * https://github.com/iTimeTraveler/DataStructureAndAlgorithms/blob/master/src/main/java/multithread/ProducerConsumer.java
 * @Author lanwenquan
 * @Date 2020/06/12 16:34
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


/**
 * 生产者消费者模式：使用Object.wait() / notify()方法实现
 *
 * wait()：当缓冲区已满/空时，生产者/消费者线程停止自己的执行，放弃锁，使自己处于等待状态，让其他线程执行。
 * notify()：当生产者/消费者向缓冲区放入/取出一个产品时，向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态。
 */
public class ProducerConsumer {
    private static final int CAPACITY = 5;
    private static int i = 0;

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();

        Thread producer1 = new Producer("P-1", queue, CAPACITY);
        Thread producer2 = new Producer("P-2", queue, CAPACITY);

        Thread consumer1 = new Consumer("C1", queue, CAPACITY);
        Thread consumer2 = new Consumer("C2", queue, CAPACITY);
        Thread consumer3 = new Consumer("C3", queue, CAPACITY);

        producer1.start();
        producer2.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();
    }

    /**
     * 生产者
     */
    public static class Producer extends Thread {
        private Queue<Integer> queue;
        String name;
        int maxSize;

        public Producer(String name, Queue<Integer> queue, int maxSize) {
            super(name);
            this.name = name;
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == maxSize) {
                        try {
                            System.out.println("Queue is full, Producer[" + name + "] thread waiting for " + "consumer to take something from queue.");
                            queue.wait();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    System.out.println("[" + name + "] Producing value : +" + i);
                    queue.offer(i++);
                    queue.notifyAll();
                }

                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 消费者
     */
    public static class Consumer extends Thread {
        private Queue<Integer> queue;
        String name;
        int maxSize;

        public Consumer(String name, Queue<Integer> queue, int maxSize) {
            super(name);
            this.name = name;
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            System.out.println("Queue is empty, Consumer[" + name + "] thread is waiting for Producer");
                            queue.wait();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    int x = queue.poll();
                    System.out.println("[" + name + "] Consuming value : " + x);
                    queue.notifyAll();
                }

                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
