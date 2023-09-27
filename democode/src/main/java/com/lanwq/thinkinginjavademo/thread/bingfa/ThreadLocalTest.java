package com.lanwq.thinkinginjavademo.thread.bingfa;

/**
 * @ClassName ThreadLocalTest
 * @Description TODO ThreadLocal
 * Java中的ThreadLocal类可以让你创建的变量只被同一个线程进行读和写操作。
 * 因此，尽管有两个线程同时执行一段相同的代码，而且这段代码又有一个指向同一个ThreadLocal变量的引用，
 * 但是这两个线程依然不能看到彼此的ThreadLocal变量域。
 * @Author lanwenquan
 * @Date 2020/5/21 16:27
 */
public class ThreadLocalTest {
    private ThreadLocal threadLocal = new ThreadLocal();

    public void testSetValue() {
        threadLocal.set("a thread local value");
    }

    public void testGetValue() {
        String threadLocalValue = (String) threadLocal.get();
    }

    // 泛型
    public void testGeneric() {
        ThreadLocal<String> myThreadLocal = new ThreadLocal<String>();
        myThreadLocal.set("Hello ThreadLocal");

        String threadLocalValue = myThreadLocal.get();
    }

    // 初始化值，所有的线程都可以获取到的值，对所有线程可见, 应该避免所有线程有一个相同的值
    public void testInitialValue() {
        ThreadLocal myThreadLocal = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return String.valueOf(System.currentTimeMillis());
            }
        };
    }
}