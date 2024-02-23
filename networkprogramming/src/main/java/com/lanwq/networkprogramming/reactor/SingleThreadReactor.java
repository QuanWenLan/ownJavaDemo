package com.lanwq.networkprogramming.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Vin lan
 * @className SingleThreadReactor
 * @description 单线程的 Reactor 模式
 * @createTime 2023-03-28  10:18
 * https://www.cnblogs.com/crazymakercircle/p/9833847.html
 * <p>
 * （1）Reactor：负责响应IO事件，当检测到一个新的事件，将其发送给相应的Handler去处理；新的事件包含连接建立就绪、读就绪、写就绪等。
 * <p>
 * （2）Handler：将自身（handler）与事件绑定，负责事件的处理，完成channel的读入，完成处理业务逻辑后，负责将结果写出channel。
 **/
public class SingleThreadReactor implements Runnable {
    private final Selector selector;
    private final ServerSocketChannel serverSocket;

    SingleThreadReactor(int port) throws IOException {
        //Reactor初始化
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        //非阻塞
        serverSocket.configureBlocking(false);
        //分步处理,第一步,接收accept事件
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        //attach callback object, Acceptor
        sk.attach(new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext()) {
                    //Reactor负责dispatch收到的事件
                    dispatch((SelectionKey) (it.next()));
                }
                selected.clear();
            }
        } catch (IOException ex) { /* ... */ }
    }

    private void dispatch(SelectionKey k) {
        Runnable r = (Runnable) (k.attachment());
        //调用之前注册的callback对象
        if (r != null) {
            r.run();
        }
    }

    /**
     * inner class
     */
    class Acceptor implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocket.accept();
                if (channel != null) {
                    new SingleThreadHandler(selector, channel);
                }
            } catch (IOException ex) { /* ... */ }
        }
    }
}
