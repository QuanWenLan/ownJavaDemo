package com.lanwq.thread.printAbc;

/**
 * @author Vin lan
 * @className PrintAbcBySynchronized
 * @description
 * @createTime 2023-04-04  11:49
 **/
public class PrintAbcBySynchronized {
    /**
     * 从大的方向上来讲，该问题为三线程间的同步唤醒操作，主要的目的就是ThreadA->ThreadB->ThreadC->ThreadA循环执行三个线程。
     * 为了控制线程执行的顺序，那么就必须要确定唤醒、等待的顺序，所以每一个线程必须同时持有两个对象锁，才能进行打印操作。
     * 一个对象锁是prev，就是前一个线程所对应的对象锁，其主要作用是保证当前线程一定是在前一个线程操作完成后（即前一个线程释放了其对应的对象锁）才开始执行。
     * 还有一个锁就是自身对象锁。主要的思想就是，为了控制执行的顺序，必须要先持有prev锁（也就前一个线程要释放其自身对象锁），然后当前线程再申请自己对象锁，两者兼备时打印。
     * 之后首先调用self.notify()唤醒下一个等待线程（注意notify不会立即释放对象锁，只有等到同步块代码执行完毕后才会释放），再调用prev.wait()立即释放prev对象锁，当前线程进入休眠，等待其他线程的notify操作再次唤醒
     */
    public static class ThreadPrinter implements Runnable {
        private String name;
        private final Object prev;
        private final Object self;

        private ThreadPrinter(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while (count > 0) {// 多线程并发，不能用if，必须使用while循环
                synchronized (prev) { // 先获取 prev 锁
                    synchronized (self) { // 再获取 self 锁
                        System.out.print(name);// 打印
                        count--;

                        self.notifyAll();// 唤醒其他线程竞争self锁，注意此时self锁并未立即释放。
                    }
                    // 此时执行完self的同步块，这时self锁才释放。
                    try {
                        if (count == 0) {// 如果count==0,表示这是最后一次打印操作，通过notifyAll操作释放对象锁。
                            prev.notifyAll();
                        } else {
                            prev.wait(); // 立即释放 prev锁，当前线程休眠，等待唤醒
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadPrinter pa = new ThreadPrinter("A", c, a);
        ThreadPrinter pb = new ThreadPrinter("B", a, b);
        ThreadPrinter pc = new ThreadPrinter("C", b, c);

        new Thread(pa).start();
        // 保证初始ABC的启动顺序
        Thread.sleep(10);
        new Thread(pb).start();
        Thread.sleep(10);
        new Thread(pc).start();
        Thread.sleep(10);
        /*
         * A B C线程先后执行
         *
         * A
         * 获取到C的锁，和A自己的锁，打印A
         * （其他的A运行也要先获取到C的锁，和A自己的锁，但此时只释放了A自己的锁，然后唤醒notifyAll其他所有的阻塞B和C
         * C的锁是没有释放，所以获取不到，也就不能同时打印A）
         * 执行结束后C调用wait释放C的锁。
         * B
         * 此时B线程已经执行了，先获取A的锁，再B自己的锁，因为B获取A锁失败会阻塞到获取锁的那里等待A锁被释放，上面A线程执行完了之后，
         * 会释放自己的锁，并唤醒其他所有阻塞B锁的线程notifyAll，
         * 当A线程执行完释放了锁之后B线程就会获取到A锁，随后在获取B自己的锁，然后打印B，
         * 结束后A调用wait释放自己锁
         * C
         * 此时C也执行了，但是C需要先获取B，在获取C。但是B线程自己先获取了，释放了之后C才能
         * 获取到，所以需要调用notifyAll，唤醒所有阻塞C锁的线程
         */
    }
}
