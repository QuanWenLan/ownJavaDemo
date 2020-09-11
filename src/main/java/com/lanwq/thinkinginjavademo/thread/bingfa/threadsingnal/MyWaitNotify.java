package com.lanwq.thinkinginjavademo.thread.bingfa.threadsingnal;

/**
 * @ClassName MyWaitNotify
 * @Description TODO 线程之间的通信：3，wait(),notify()和notifyAll()，线程如何通过这些方法通信
 * @Author lanwenquan
 * @Date 2020/5/25 16:33
 */

/** http://ifeve.com/thread-signaling/
 * 一个线程一旦调用了任意对象的wait()方法，就会变为非运行状态，直到另一个线程调用了同一个对象的notify()方法。
 * 为了调用wait()或者notify()，线程必须先获得那个对象的锁。也就是说，线程必须在同步块里调用wait()或者notify()。
 * 以下是MySignal的修改版本——使用了wait()和notify()的MyWaitNotify：
 * A thread that calls wait() on any object becomes inactive until another thread calls notify() on that object.
 * In order to call either wait() or notify() the calling thread must first obtain the lock on that object.
 * In other words, the calling thread must call wait() or notify() from inside a synchronized block.
 * Here is a modified version of MySignal called MyWaitNotify that uses wait() and notify().
 */
public class MyWaitNotify {

    private MonitorObject myMonitorObject = new MonitorObject();

    /**
     * 如你所见，不管是等待线程还是唤醒线程都在同步块里调用wait()和notify()。这是强制性的！一个线程如果没有持有对象锁，
     * 将不能调用wait()，notify()或者notifyAll()。否则，会抛出IllegalMonitorStateException异常。
     */
    public void doWait() {
        synchronized (myMonitorObject) {
            try {
                // 当前线程必须拥有该对象的监视器（monitor）。这个线程释放这个监视器的所有权，并等待直到另一个线程通过调用notify()或者notifyAll()方法
                // 来通知并唤醒正在等待这个对象的监视器的所有线程。
                myMonitorObject.wait();
            } catch (InterruptedException e) {
                //...
            }
        }
    }

    public void doNotify() {
        synchronized (myMonitorObject) {
            // 唤醒一个正在等待这个对象的监视器的线程。如果有很多线程正在等待这个对象监视器，则唤醒其中的一个。选择是任意的，并且由实施的判断发生。
            // 线程通过调用wait()方法来等待一个对象的监视器。唤醒的线程不能执行直到放弃了这个对象的锁。
            myMonitorObject.notify();
        }
    }
}

