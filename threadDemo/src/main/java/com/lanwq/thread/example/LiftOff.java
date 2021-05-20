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
        LiftOff off1 = new LiftOff();
        System.out.println(off1.id +" taskCount: " + off1.taskCount);// 由于static属性的原因，会一直自增
        LiftOff off2 = new LiftOff();
        System.out.println(off2.id+" taskCount: " + off1.taskCount);
    }
}
// 任务的run方法通常总会有某种形式的循环，使得任务一直运行下去直到不在需要，所以要设定跳出循环的条件（有一种选择是直接从run()返回），
// 否则它将永远运行下去
/**
 * Thread.yield(); 调用的是对 <i>线程调度器</i> （Java线程机制的一部分，可以将CPU从一个线程转移到另一个线程）的一种建议。
 * 它在申明:<p>"我已经执行完生命周期中最重要的部分了，此刻正是切换给其他任务执行一段时间的大好时机"</p>。这个完全是选择性的，这里使用是因为可以看到
 * 任务换进换出的证据。
  */