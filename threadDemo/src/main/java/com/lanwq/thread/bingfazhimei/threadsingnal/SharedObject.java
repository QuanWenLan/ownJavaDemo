package com.lanwq.thread.bingfazhimei.threadsingnal;

/**
 * @ClassName SharedObject
 * @Description TODO 线程之间的通信：1,通过共享对象来进行线程通信
 * @Author lanwenquan
 * @Date 2020/5/25 16:13
 */
public class SharedObject {

    /**
     * TODO 1，共享变量
     * 线程A和B必须获得指向一个SharedObject共享实例的引用，以便进行通信。如果它们持有的引用指向不同的SharedObject实例，
     * 那么彼此将不能检测到对方的信号。需要处理的数据可以存放在一个共享缓存区里，它和MySignal实例是分开存放的。
     */
    private boolean hasDataToProcess = false;

    public synchronized void setHasDataToProcess(boolean hasData) {
        this.hasDataToProcess = hasData;
    }

    public synchronized boolean hasDataToProcess() {
        return this.hasDataToProcess;
    }

    /**
     * TODO 2,忙等待准备处理数据的线程B正在等待数据变为可用。
     * 换句话说，它在等待线程A的一个信号，这个信号使hasDataToProcess()返回true。线程B运行在一个循环里，以等待这个信号
     */
    private SharedObject signal = new SharedObject();

    public void doProcessData() {
        while (!signal.hasDataToProcess()) {
            System.out.println("处理数据。。。");
        }
    }
}