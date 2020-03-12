package com.lanwq.thread;

/**
 * @ClassName LiftOff
 * @Description TODO
 * @Author Administrator
 * @Date 2019/11/25 19:27
 */
public class LiftOff implements Runnable{
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {}

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status(){
        return " #" + id + "(" + (countDown > 0 ? countDown : " Liftoff") + "),";
    }
    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.print(status());
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        LiftOff off = new LiftOff();
        off.run();
    }
}