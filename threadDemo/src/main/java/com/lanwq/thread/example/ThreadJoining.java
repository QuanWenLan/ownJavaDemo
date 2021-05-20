package com.lanwq.thread.example;

/**
 * @author Vin lan
 * @className ThreadJoining
 * @description 加入一个线程
 * 一个线程可以在其他线程之上调用join()方法，其效果是等待一段时间直到第二个线程结束才继续执行。
 * 如果某个线程在另外一个线程t上调用 t.join()，此线程将会被挂起，直到目标t结束才恢复（即t.isAlive())
 * 也可以在调用join()时带上一个超时参数（单位可以是毫秒，或者毫秒和纳秒）
 * ，这样如果目标线程在这段时间到期时还没有结束的话，join()方法总能返回。
 * @createTime 2021-03-22  17:11
 **/
public class ThreadJoining {
    public static void main(String[] args) {
        Sleeper sleeper = new Sleeper("Sleeper", 1500),
                grumpy = new Sleeper("Grumpy", 1500);
        Joiner joiner = new Joiner("Dopey", sleeper),
                doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();
    }
}

class Sleeper extends Thread {
    private int duration;

    public Sleeper(String name, int sleepTime) {
        super(name);
        this.duration = sleepTime;
        start();  // 在构造器中启动线程
    }

    @Override
    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted. isInterrupted(): " + isInterrupted());
            return;
        }
        System.out.println(getName() + " has awakened");
    }
}

class Joiner extends Thread {
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println(getName() + " join completed");
    }
}
