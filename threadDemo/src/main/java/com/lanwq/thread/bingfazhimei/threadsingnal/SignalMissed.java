package com.lanwq.thread.bingfazhimei.threadsingnal;

/**
 * TODO 线程之间的通信：3，线程之间的通信，信号丢失
 * 为什么这么说？因为当一个线程调用wait方法之前，就已经调用了notify方法，那么这个唤醒的信号就丢失了，那么这个线程将会一直
 * 等待下去，直到下一个线程调用notify方法或者notifyAll
 */
public class SignalMissed {
    MonitorObject myMonitorObject = new MonitorObject();
    boolean wasSignalled = false;

    public void doWait() {
        synchronized (myMonitorObject) {
            if (!wasSignalled) {
                try {
                    myMonitorObject.wait();
                } catch (InterruptedException e) {
//                    ...
                }
            }
            //clear signal and continue running.
            wasSignalled = false;
        }
    }

    public void doNotify() {
        synchronized (myMonitorObject) {
            wasSignalled = true;
            myMonitorObject.notify();
        }
    }
}
