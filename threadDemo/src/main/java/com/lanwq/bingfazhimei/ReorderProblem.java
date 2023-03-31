package com.lanwq.bingfazhimei;

/**
 * @author Vin lan
 * @className ReorderProblem
 * @description 重排序问题
 * @createTime 2021-10-29  14:48
 **/
public class ReorderProblem {
    public static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (ready) {
                    System.out.println(num + num);
                }
                System.out.println("read thread...");
            }
        }
    }

    public static class WriteThread extends Thread {
        @Override
        public void run() {
            num = 2;
            ready = true;
            System.out.println("write thread is over...");
        }
    }
    private static int num = 0;
    private static boolean ready = false;

    public static void main(String[] args) throws InterruptedException{
        /*ReadThread readThread = new ReadThread();
        readThread.start();
        WriteThread writeThread = new WriteThread();
        writeThread.start();
        Thread.sleep(10);
        readThread.interrupt();
        System.out.println("main exit");*/

        long[][] array = new long[1024][1024];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1024; i++) {
            for (int j = 0; j < 1024; j++) {
                array[i][j] = i * 2 + j;
//                array[j][i] = i * 2 + j;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("cache time:" + (endTime - startTime));
    }
}
