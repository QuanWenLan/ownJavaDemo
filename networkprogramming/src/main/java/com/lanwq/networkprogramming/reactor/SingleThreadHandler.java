package com.lanwq.networkprogramming.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author Vin lan
 * @className SingleThreadHandler
 * @description
 * @createTime 2023-03-28  10:19
 **/
public class SingleThreadHandler implements Runnable {
    private final SocketChannel channel;
    private final SelectionKey sk;
    private ByteBuffer input = ByteBuffer.allocate(SystemConfig.INPUT_SIZE);
    private ByteBuffer output = ByteBuffer.allocate(SystemConfig.SEND_SIZE);
    private static final int READING = 0, SENDING = 1;
    private int state = READING;

    SingleThreadHandler(Selector selector, SocketChannel c) throws IOException {
        channel = c;
        c.configureBlocking(false);
        // Optionally try first read now
        sk = channel.register(selector, 0);
        //将Handler作为callback对象
        sk.attach(this);
        //第二步,注册Read就绪事件
        sk.interestOps(SelectionKey.OP_READ);
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

    private void read() throws IOException {
        channel.read(input);
        if (inputIsComplete()) {

            process();

            state = SENDING;
            // Normally also do first write now

            //第三步,接收write就绪事件
            sk.interestOps(SelectionKey.OP_WRITE);
        }
    }

    private void send() throws IOException {
        channel.write(output);

        //write完就结束了, 关闭select key
        if (outputIsComplete()) {
            sk.cancel();
        }
    }
}
