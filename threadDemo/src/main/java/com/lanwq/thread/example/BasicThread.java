package com.lanwq.thread.example;

/**
 * @ClassName BasicThread
 * @Description
 * @Author lanwenquan
 * @Date 2020/05/28 15:30
 */
public class BasicThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new LiftOff());
        thread.start(); // 为该线程执行必须的初始化操作
        System.out.println("waiting for liftoff");
    }
}
/**
 * 解释：调用了start()方法之后，方法迅速返回了，因为waiting for liftoff 消息在倒计时完成之前就已经出现了。实际上，产生的是对
 * <code>LiftOff.run()</code>的方法调用，并且这个方法还没有完成，但是因为<code>LiftOff.run()</code>是由不同的线程执行的，
 * 因为我们仍旧可以执行main()线程中的其他操作（这种能力并不局限于main线程，任何线程都可以启动另一个线程）。
 */