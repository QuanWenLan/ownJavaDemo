package com.lanwq.thread.example;

/**
 * @ClassName LiftOff
 * @Description TODO
 * @Author Administrator
 * @Date 2019/11/25 19:27
 */
public class LiftOff implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return " #" + id + "(" + (countDown > 0 ? countDown : "Liftoff") + "),";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.print(status());
            Thread.yield();// 可以将CPU从一个线程转移给另一个线程。
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LiftOff off = new LiftOff();
        off.run(); // 这个不是一个单独的线程启动的，而是在main()中直接调用的，在main线程里面
    }
}