package com.lanwq;

/**
 * @author Lan
 * @createTime 2023-06-26  17:06
 **/
public class DepotTest {
    static class Consumer {
        private Depot depot;

        public Consumer(Depot depot) {
            this.depot = depot;
        }

        public void consume(int no) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    depot.consume(no);
                }
            }, no + " consume thread").start();
        }
    }

    static class Producer {
        private Depot depot;

        public Producer(Depot depot) {
            this.depot = depot;
        }

        public void produce(int no) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    depot.produce(no);
                }
            }, no + " produce thread").start();
        }
    }
//    原文链接：https://pdai.tech/md/java/thread/java-thread-x-lock-AbstractQueuedSynchronizer.html

    public static void main(String[] args) {
        Depot depot = new Depot(500);
        new Producer(depot).produce(500);
        new Producer(depot).produce(200);
        new Consumer(depot).consume(500);
        new Consumer(depot).consume(200);
    }
}
