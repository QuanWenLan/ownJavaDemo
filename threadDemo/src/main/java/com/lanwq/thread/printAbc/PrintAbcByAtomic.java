package com.lanwq.thread.printAbc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Vin lan
 * @className PrintAbcByAtomic
 * @description
 * @createTime 2023-04-04  11:42
 **/
public class PrintAbcByAtomic {

    private AtomicInteger ai = new AtomicInteger(0);
    private static final int MAX_SYC_VALUE = 3 * 10;

    private class RunnableA implements Runnable {
        @Override
        public void run() {
            while (ai.get() < MAX_SYC_VALUE-1) {
                if (ai.get() % 3 == 0) {
                    System.out.print("A");
                    ai.getAndIncrement();
                }
            }

        }
    }

    private class RunnableB implements Runnable {
        @Override
        public void run() {
            while (ai.get() < MAX_SYC_VALUE) {
                if (ai.get() % 3 == 1) {
                    System.out.print("B");
                    ai.getAndIncrement();
                }
            }

        }
    }

    private class RunnableC implements Runnable {
        @Override
        public void run() {
            while (ai.get() < MAX_SYC_VALUE) {
                if (ai.get() % 3 == 2) {
                    System.out.println("C");
                    ai.getAndIncrement();
                }
            }

        }
    }


    public static void main(String[] args) {
        PrintAbcByAtomic atomicAbc = new PrintAbcByAtomic();
        ExecutorService service = Executors.newFixedThreadPool(3);

        service.execute(atomicAbc.new RunnableA());
        service.execute(atomicAbc.new RunnableB());
        service.execute(atomicAbc.new RunnableC());

        service.shutdown();
    }
}


