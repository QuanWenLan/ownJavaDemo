package com.lanwq.thread.bingfazhimei;

/**
 * @ClassName ThreadCreate
 * @Description TODO 线程创建的三种方式
 * @Author lanwenquan
 * @Date 2020/5/13 16:19
 */
public class ThreadCreate {
    static void test1() {
        Thread thread = new Thread();
        thread.start();
        System.out.println("[" + thread.getName() + "] 启动");
    }

    static void test2() {
        MyThread thread = new MyThread();
        thread.start();
        System.out.println("[" + thread.getName() + "] 启动");
    }

    static void test3() {
        // 创建runnable的三种方式
//        Thread thread = new Thread(new MyRunnable());
        Runnable runnable = () -> { System.out.println("Lambda Runnable running"); };
        Thread thread0 = new Thread(runnable);
        Thread thread = new Thread(new Runnable(){

            @Override
            public void run() {
                System.out.println("MyRunnable running");
            }
        });
        thread.start();
        System.out.println("[" + thread.getName() + "] 启动");
    }

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }
}

class MyThread extends Thread {

    //一旦线程启动后start方法就会立即返回，而不会等待到run方法执行完毕才返回。
    // 就好像run方法是在另外一个cpu上执行一样。当run方法执行后，将会打印出字符串MyThread running。
    @Override
    public void run() {
        System.out.println("MyThread running");
    }
}

class MyRunnable implements Runnable {
    public void run(){
        System.out.println("MyRunnable running");
    }
}