package com.lanwq.thread.bingfazhimei;

/**
 * @ClassName TestObjectMethodNotSafeByThread
 * @Description TODO 测试多个线程操作一个类实例的方法出现的问题
 * @Author lanwenquan
 * @Date 2020/5/14 8:28
 */
public class TestObjectMethodNotSafeByThreads {
    protected int count;

    public synchronized void add(int value) {
        this.count = this.count + value;
        System.out.println("添加value:" + value + " 后，counter值：" + this.count);
    }
}


class Test {
    public static void main(String[] args) throws InterruptedException {
        TestObjectMethodNotSafeByThreads counter = new TestObjectMethodNotSafeByThreads();
        new Thread(new ThreadOne(counter), "one").start();
        new Thread(new ThreadTwo(counter), "two").start();
    }
}

class ThreadOne implements Runnable {
    private TestObjectMethodNotSafeByThreads counter;

    public ThreadOne(TestObjectMethodNotSafeByThreads counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("当前线程[" + Thread.currentThread().getName() + "] 调用 add(" + i + ")");
            /*try {
                if(i == 3)
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            counter.add(i);
        }
    }
}

class ThreadTwo implements Runnable {
    private TestObjectMethodNotSafeByThreads counter;

    public ThreadTwo(TestObjectMethodNotSafeByThreads counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("当前线程[" + Thread.currentThread().getName() + "] 调用 add(" + i + ")");
            /*try {
                if (i == 3)
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            counter.add(i);
        }
    }
}