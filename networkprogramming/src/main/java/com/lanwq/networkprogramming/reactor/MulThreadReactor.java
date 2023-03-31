package com.lanwq.networkprogramming.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Vin lan
 * @className MulThreadReactor
 * @description 多线程的 Reactor 模式
 * @createTime 2023-03-28  10:29
 * （1）将Handler处理器的执行放入线程池，多线程进行业务处理。
 * <p>
 * （2）而对于Reactor而言，可以仍为单个线程。如果服务器为多核的CPU，为充分利用系统资源，可以将Reactor拆分为两个线程。
 **/
public class MulThreadReactor implements Runnable {
    private final SocketChannel channel;
    private final SelectionKey selectionKey;
    private ByteBuffer input = ByteBuffer.allocate(SystemConfig.INPUT_SIZE);
    private ByteBuffer output = ByteBuffer.allocate(SystemConfig.SEND_SIZE);
    private static final int READING = 0, SENDING = 1;
    private int state = READING;
    private ExecutorService pool = Executors.newFixedThreadPool(2);
    private static final int PROCESSING = 3;

    MulThreadReactor(Selector selector, SocketChannel c) throws IOException {
        channel = c;
        c.configureBlocking(false);
        // Optionally try first read now
        selectionKey = channel.register(selector, 0);
        //将Handler作为callback对象
        selectionKey.attach(this);
        //第二步,注册Read就绪事件
        selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    private boolean inputIsComplete() {
        /* ... */
        return false;
    }

    private boolean outputIsComplete() {

        /* ... */
        return false;
    }

    private void process() {
        /* ... */
        return;
    }

    @Override
    public void run() {
        try {
            if (state == READING) {
                read();
            } else if (state == SENDING) {
                send();
            }
        } catch (IOException ex) { /* ... */ }
    }


    private synchronized void read() throws IOException {
        // ...
        channel.read(input);
        if (inputIsComplete()) {
            state = PROCESSING;
            //使用线程pool异步执行
            pool.execute(new Processor());
        }
    }

    private void send() throws IOException {
        channel.write(output);

        //write完就结束了, 关闭select key
        if (outputIsComplete()) {
            selectionKey.cancel();
        }
    }

    private synchronized void processAndHandOff() {
        process();
        state = SENDING;
        // or rebind attachment
        //process完,开始等待write就绪
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    class Processor implements Runnable {
        @Override
        public void run() {
            processAndHandOff();
        }
    }

}
